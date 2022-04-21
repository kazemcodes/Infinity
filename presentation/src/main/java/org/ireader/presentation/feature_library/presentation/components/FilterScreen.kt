package org.ireader.presentation.feature_library.presentation.components

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ireader.domain.models.FilterType


@Composable
fun FilterScreen(
    filters: List<FilterType>,
    addFilters: (FilterType) -> Unit,
    removeFilter: (FilterType)-> Unit
) {
    Column(Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {
        val items = listOf(
            FilterItem("Unread", FilterType.Unread),
            FilterItem("Completed", FilterType.Completed),
            FilterItem("Downloaded", FilterType.Downloaded),
        )
        items.forEach { filter ->
            CheckBoxWithText(filter.name,
                filters.contains(filter.type)) {
                if (!filters.contains(filter.type)) {
                    addFilters(filter.type)
                } else {
                    removeFilter(filter.type)
                }
            }
        }

    }
}


private data class FilterItem(
    val name: String,
    val type: FilterType,
)
