package org.ireader.presentation.feature_sources.presentation.global_search.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.ireader.domain.models.entities.Book
import tachiyomi.source.Source
import javax.inject.Inject

interface GlobalSearchState {
    var searchItems: List<SearchItem>
    var query: String

}

open class GlobalSearchStateImpl @Inject constructor() : GlobalSearchState {
    override var searchItems: List<SearchItem> by mutableStateOf(emptyList())
    override var query: String by mutableStateOf("")
}

data class SearchItem(
    val source: Source,
    val items: List<Book> = emptyList(),
    val loading: Boolean = false,
)