package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E13Answer() {
    // Immutable approach: state is a new list each time
    var items by remember { mutableStateOf(listOf("Item 1", "Item 2", "Item 3")) }
    var nextId by remember { mutableIntStateOf(4) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Immutable list state (${items.size} items):",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(4.dp))

        items.forEach { item ->
            Text(text = "- $item", modifier = Modifier.padding(vertical = 1.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            // Creating a new list triggers recomposition because the reference changes.
            items = items + "Item $nextId"
            nextId++
        }) {
            Text("Add Item")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                if (items.isNotEmpty()) {
                    items = items.dropLast(1)
                }
            },
            enabled = items.isNotEmpty()
        ) {
            Text("Remove Last")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { items = emptyList() }) {
            Text("Clear All")
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Compose detects state changes by reference equality. " +
                "Mutating a list in place (e.g., list.add()) does NOT trigger recomposition " +
                "because the reference stays the same. Always create new collections: " +
                "state = state + newItem.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
