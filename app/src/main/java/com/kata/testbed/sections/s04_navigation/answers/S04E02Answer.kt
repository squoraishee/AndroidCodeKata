package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private sealed interface NavState {
    data object List : NavState
    data class Detail(val itemId: String, val itemName: String) : NavState
}

private data class Item(val id: String, val name: String, val description: String)

@Composable
fun S04E02Answer() {
    val items = remember {
        listOf(
            Item("1", "Kotlin", "Modern JVM language"),
            Item("2", "Compose", "Declarative UI toolkit"),
            Item("3", "Coroutines", "Async programming")
        )
    }
    // Navigation state carries the argument (itemId) to the detail screen.
    // In real Navigation, you'd use: navController.navigate("detail/$itemId")
    var navState by remember { mutableStateOf<NavState>(NavState.List) }

    Column(modifier = Modifier.padding(16.dp)) {
        when (val state = navState) {
            is NavState.List -> {
                Text("Items (tap to view detail):", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                items.forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { navState = NavState.Detail(item.id, item.name) }
                    ) {
                        Text(
                            text = "${item.id}. ${item.name}",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
            is NavState.Detail -> {
                val item = items.find { it.id == state.itemId }
                Text("Detail Screen", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Item ID: ${state.itemId}", style = MaterialTheme.typography.bodyLarge)
                Text("Name: ${item?.name ?: "Unknown"}")
                Text("Description: ${item?.description ?: "N/A"}")
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { navState = NavState.List }) {
                    Text("Back to List")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Route arguments pass data between screens. " +
                "In Compose Navigation: navigate(\"detail/\$id\") and " +
                "retrieve with backStackEntry.arguments?.getString(\"id\").",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
