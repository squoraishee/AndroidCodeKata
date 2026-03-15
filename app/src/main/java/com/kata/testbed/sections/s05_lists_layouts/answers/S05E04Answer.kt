package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class CarouselCard(val title: String, val description: String)

@Composable
fun S05E04Answer() {
    val cards = listOf(
        CarouselCard("Kotlin", "Modern, concise, and safe programming language"),
        CarouselCard("Compose", "Declarative UI toolkit for Android"),
        CarouselCard("Coroutines", "Lightweight concurrency framework"),
        CarouselCard("Room", "SQLite abstraction for Android persistence"),
        CarouselCard("Hilt", "Dependency injection built on Dagger"),
        CarouselCard("Retrofit", "Type-safe HTTP client for Android"),
        CarouselCard("Navigation", "Framework for navigating between screens")
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Horizontal Card Carousel:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // LazyRow is the horizontal equivalent of LazyColumn.
        // contentPadding adds space at the start/end without clipping items.
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(cards) { card ->
                Card(
                    modifier = Modifier.width(200.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = card.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = card.description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 4.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
