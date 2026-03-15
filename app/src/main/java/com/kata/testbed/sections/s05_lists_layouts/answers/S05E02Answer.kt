package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class NamedItem(val id: Int, val name: String)

@Composable
fun S05E02Answer() {
    // Each item has a stable ID that survives reordering.
    // Without keys, LazyColumn uses position -- shuffling causes wrong state to stick.
    val items = remember {
        mutableStateListOf(
            NamedItem(1, "Alpha"),
            NamedItem(2, "Bravo"),
            NamedItem(3, "Charlie"),
            NamedItem(4, "Delta"),
            NamedItem(5, "Echo"),
            NamedItem(6, "Foxtrot"),
            NamedItem(7, "Golf"),
            NamedItem(8, "Hotel"),
            NamedItem(9, "India"),
            NamedItem(10, "Juliet")
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Tap Shuffle and notice items move correctly:",
            style = MaterialTheme.typography.titleMedium
        )

        Button(
            onClick = { items.shuffle() },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Shuffle")
        }

        LazyColumn(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
        ) {
            // key = { it.id } tells LazyColumn to track each item by its ID
            // rather than by its position in the list.
            items(items, key = { it.id }) { item ->
                Text(
                    text = "${item.id}. ${item.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                        .animateItem(), // smooth reorder animation
                    style = MaterialTheme.typography.bodyLarge
                )
                HorizontalDivider()
            }
        }
    }
}
