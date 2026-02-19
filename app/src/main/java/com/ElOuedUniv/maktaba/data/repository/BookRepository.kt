package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book


class BookRepository {

    private val booksList = listOf(
        Book(isbn = "978-0132350884", title = "Clean Code", nbPages = 464),
        Book(isbn = "978-0135957059", title = "The Pragmatic Programmer", nbPages = 352),
        Book(isbn = " 978-0201633610", title = "Design Patterns", nbPages = 416),
        Book(isbn = "798-0134757599", title = "Refactoring", nbPages = 448),
        Book(isbn = "978-0596007126", title = "Head First Design Patterns", nbPages = 692 ),
        Book(isbn = "978-1599869773", title = "The Art of War", nbPages = 68),
        Book(isbn = "978-1985086593", title = "Operating Systems: Three Easy Pieces", nbPages = 710),
        Book(isbn = "978-1491927571", title = "Linux Pocket Guide", nbPages = 204),
        Book(isbn = "978-0199678112", title = "Superintelligence", nbPages = 352),
        Book(isbn = "978-0262510875", title = "Structure and Interpretation of Computer Programs", nbPages = 657)
    )

    fun getAllBooks(): List<Book> {
        return booksList
    }

    fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }
}
