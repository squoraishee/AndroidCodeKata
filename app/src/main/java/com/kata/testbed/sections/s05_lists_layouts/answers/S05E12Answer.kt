package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S05E12Answer() {
    val listState = rememberLazyListState()

    // Collapse fraction: 0f = fully expanded, 1f = fully collapsed.
    // We derive it from the first visible item index and scroll offset.
    val collapseFraction by remember {
        derivedStateOf {
            if (listState.firstVisibleItemIndex > 0) {
                1f
            } else {
                val offset = listState.firstVisibleItemScrollOffset.toFloat()
                // Collapse over the first 200 pixels of scrolling
                (offset / 200f).coerceIn(0f, 1f)
            }
        }
    }

    val expandedHeight = 120.dp
    val collapsedHeight = 48.dp
    // Interpolate between expanded and collapsed heights
    val headerHeight = expandedHeight - (expandedHeight - collapsedHeight) * collapseFraction

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Collapsible Header:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        ) {
            // Collapsible header that shrinks as the list scrolls
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "My Header",
                        style = if (collapseFraction < 0.5f)
                            MaterialTheme.typography.headlineSmall
                        else
                            MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    if (collapseFraction < 0.5f) {
                        Text(
                            text = "Scroll the list to collapse me",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // The list content that drives the collapse
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(30) { index ->
                    Text(
                        text = "List item #$index",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
