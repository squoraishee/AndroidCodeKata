package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun S05E03Answer() {
    // Grouped data: category name mapped to its items
    val groupedItems = mapOf(
        "Fruits" to listOf("Apple", "Banana", "Cherry", "Date", "Elderberry"),
        "Vegetables" to listOf("Asparagus", "Broccoli", "Carrot", "Daikon"),
        "Grains" to listOf("Barley", "Corn", "Oats", "Rice", "Wheat"),
        "Dairy" to listOf("Butter", "Cheese", "Cream", "Yogurt")
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Sticky Headers with Grouped Items:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        ) {
            groupedItems.forEach { (category, items) ->
                // stickyHeader stays pinned at the top while its section is visible.
                // This requires ExperimentalFoundationApi.
                stickyHeader {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                items(items) { item ->
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 10.dp)
                    )
                }
            }
        }
    }
}
