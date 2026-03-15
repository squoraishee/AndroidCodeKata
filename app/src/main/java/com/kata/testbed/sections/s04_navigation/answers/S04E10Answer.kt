package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private val mockData = mapOf(
    1 to "Kotlin: A modern JVM language by JetBrains.",
    2 to "Compose: Android's declarative UI toolkit.",
    3 to "Coroutines: Lightweight concurrency for Kotlin."
)

@Composable
fun S04E10Answer() {
    var selectedId by remember { mutableIntStateOf(1) }
    var isLoading by remember { mutableStateOf(false) }
    var loadedData by remember { mutableStateOf("") }

    // LaunchedEffect restarts its coroutine when selectedId changes.
    // The previous coroutine is cancelled automatically.
    // This is perfect for loading data when a key changes.
    LaunchedEffect(selectedId) {
        isLoading = true
        loadedData = ""
        delay(1000) // Simulate network request
        loadedData = mockData[selectedId] ?: "Not found"
        isLoading = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Select an item to load:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            listOf(1, 2, 3).forEach { id ->
                Button(
                    onClick = { selectedId = id },
                    enabled = selectedId != id
                ) {
                    Text("Item $id")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Selected: Item $selectedId", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            Row {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.width(12.dp))
                Text("Loading...")
            }
        } else {
            Text(
                text = loadedData,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "LaunchedEffect(key) runs a coroutine when the composable enters composition " +
                "and restarts it whenever the key changes. " +
                "The previous coroutine is cancelled before the new one starts. " +
                "Ideal for loading data, starting animations, or one-time side effects.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
