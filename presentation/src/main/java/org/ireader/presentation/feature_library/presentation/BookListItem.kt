package org.ireader.presentation.feature_library.presentation

import androidx.annotation.Keep
import org.ireader.domain.models.entities.Book


sealed class BookListItem(val name: String) {

    data class Item(val book: Book) : BookListItem(book.title)
}