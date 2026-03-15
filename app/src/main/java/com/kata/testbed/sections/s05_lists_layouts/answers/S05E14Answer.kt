package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Sealed interface defines distinct content types for the list.
// The contentType parameter tells LazyColumn which composable factory to reuse,
// so a "header" slot is never recycled as a "text" slot.
private sealed interface ListContent {
    data class Header(val title: String) : ListContent
    data class TextItem(val text: String) : ListContent
    data object Divider : ListContent
}

@Composable
fun S05E14Answer() {
    val content: List<ListContent> = listOf(
        ListContent.Header("Getting Started"),
        ListContent.TextItem("Install Android Studio"),
        ListContent.TextItem("Create a new project"),
        ListContent.TextItem("Run on emulator"),
        ListContent.Divider,
        ListContent.Header("Kotlin Basics"),
        ListContent.TextItem("Variables and types"),
        ListContent.TextItem("Functions and lambdas"),
        ListContent.TextItem("Classes and objects"),
        ListContent.TextItem("Null safety"),
        ListContent.Divider,
        ListContent.Header("Compose UI"),
        ListContent.TextItem("Composable functions"),
        ListContent.TextItem("State and recomposition"),
        ListContent.TextItem("Layouts: Column, Row, Box"),
        ListContent.TextItem("Material 3 components"),
        ListContent.Divider,
        ListContent.Header("Advanced Topics"),
        ListContent.TextItem("Navigation"),
        ListContent.TextItem("Room database"),
        ListContent.TextItem("Dependency injection"),
        ListContent.TextItem("Testing strategies")
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Mixed Content Types:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        ) {
            items(
                items = content,
                // contentType tells Compose which items share the same composable structure.
                // Items with the same contentType can reuse each other's composition slots,
                // reducing the cost of scrolling through heterogeneous lists.
                contentType = { item ->
                    when (item) {
                        is ListContent.Header -> "header"
                        is ListContent.TextItem -> "text"
                        is ListContent.Divider -> "divider"
                    }
                }
            ) { item ->
                when (item) {
                    is ListContent.Header -> {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                        )
                    }
                    is ListContent.TextItem -> {
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 10.dp)
                        )
                    }
                    is ListContent.Divider -> {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp),
                            thickness = 2.dp
                        )
                    }
                }
            }
        }
    }
}
