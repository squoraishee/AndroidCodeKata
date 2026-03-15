package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun S05E07Answer() {
    val items = remember { (1..20).map { "Item $it" }.toMutableStateList() }
    var isLoading by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    // derivedStateOf avoids recomposition on every scroll pixel --
    // it only triggers when the derived value actually changes.
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisibleIndex >= totalItems - 3 && !isLoading && totalItems > 0
        }
    }

    // When shouldLoadMore becomes true, simulate loading more items
    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            isLoading = true
            delay(1000L)
            val currentSize = items.size
            items.addAll((currentSize + 1..currentSize + 10).map { "Item $it" })
            isLoading = false
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pagination (${items.size} items loaded):",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            state = listState,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        ) {
            itemsIndexed(items) { index, item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                HorizontalDivider()
            }

            // Loading indicator at the bottom
            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
