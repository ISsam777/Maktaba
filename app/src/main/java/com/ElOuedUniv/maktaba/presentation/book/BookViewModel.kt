package com.ElOuedUniv.maktaba.presentation.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import com.ElOuedUniv.maktaba.domain.usecase.GetBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow



@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()
    private val _uiEvent = MutableSharedFlow<BookUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {

            // set loading = true
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            getBooksUseCase()
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Unknown error"
                    )
                }
                .collect { bookList ->
                    _uiState.value = _uiState.value.copy(
                        books = bookList,
                        isLoading = false,
                        errorMessage = null
                    )
                }
        }
    }



    fun onAction(action: BookUiAction) {
        when (action) {

            BookUiAction.RefreshBooks -> {
                refreshBooks()
            }

            BookUiAction.OnAddBookClick -> {
                _uiState.value = _uiState.value.copy(
                    isAddingBook = true
                )
            }

            BookUiAction.OnDismissAddBook -> {
                _uiState.value = _uiState.value.copy(
                    isAddingBook = false
                )
            }

            is BookUiAction.OnAddBookConfirm -> {

                val newBook = Book(
                    title = action.title,
                    isbn = action.isbn,
                    nbPages = action.nbPages
                )

                _uiState.value = _uiState.value.copy(
                    isAddingBook = false
                )

                viewModelScope.launch {
                    addBookUseCase(newBook)
                    _uiEvent.emit(BookUiEvent.ShowSnackbar("Book added successfully"))
                }
            }

        }
    }


    fun refreshBooks() {
        loadBooks()
    }
}
