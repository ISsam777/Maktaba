package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val booksList = mutableListOf(
        Book("11111", "Clean Code", 10),
        Book("22222", "Pragmatic Programmer", 0)
    )

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1)
        .apply { tryEmit(booksList) }

    override fun getAllBooks(): Flow<List<Book>> = booksFlow

    override fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }

    override fun addBook(book: Book) {
        booksList.add(book)
        booksFlow.tryEmit(booksList.toList())
    }
}
