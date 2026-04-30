package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import io.github.jan_tennert.supabase.SupabaseClient
import io.github.jan_tennert.supabase.postgrest.postgrest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SupabaseBookRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : BookRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun getAllBooks(): Flow<List<Book>> = flow {
        val books = supabaseClient.postgrest["books"].select().decodeList<Book>()
        emit(books)
    }

    override fun getBookByIsbn(isbn: String): Book? {
        // Since the interface is not suspend, this is tricky for a network call.
        // In a real app, this should be suspend or return a Flow.
        return null
    }

    override fun addBook(book: Book) {
        scope.launch {
            supabaseClient.postgrest["books"].insert(book)
        }
    }
}
