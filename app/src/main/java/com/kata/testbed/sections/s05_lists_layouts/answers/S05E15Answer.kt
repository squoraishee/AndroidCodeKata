package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S05E15Answer() {
    var optimized by remember { mutableStateOf(false) }
    var recomposeCount by remember { mutableIntStateOf(0) }
    var toggleCount by remember { mutableIntStateOf(0) }

    // Track recompositions of this composable
    SideEffect { recomposeCount++ }

    val items = remember { (1..30).map { "Item $it" } }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Performance: ${if (optimized) "Optimized" else "Unoptimized"}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Recomposition count: $recomposeCount",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                optimized = !optimized
                recomposeCount = 0
            }) {
                Text(if (optimized) "Show Unoptimized" else "Show Optimized")
            }

            Button(
                onClick = { toggleCount++ },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Trigger Recompose")
            }
        }

        Text(
            text = "Toggle pressed $toggleCount times",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (optimized) {
            // OPTIMIZED version:
            // 1. Uses key for stable identity
            // 2. Uses remember for expensive computations
            // 3. Uses remember for lambda references
            LazyColumn(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
            ) {
                itemsIndexed(items, key = { index, _ -> index }) { index, item ->
                    // remember prevents recomputing on every recomposition
                    val formattedText = remember(item) {
                        "$item - ${item.length} chars (optimized)"
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                    ) {
                        Text(
                            text = formattedText,
                            modifier = Modifier.padding(12.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        } else {
            // UNOPTIMIZED version:
            // 1. No keys -- items tracked by position
            // 2. Computation runs on every recomposition
            // 3. New lambda created on every recomposition
            LazyColumn(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
            ) {
                itemsIndexed(items) { index, item ->
                    // No remember -- this string concatenation runs every recomposition
                    val formattedText = "$item - ${item.length} chars (unoptimized)"

                    Text(
                        text = formattedText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
