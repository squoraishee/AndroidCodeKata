package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

// Simulated search function
private fun performSearch(query: String): List<String> {
    val allItems = listOf(
        "Kotlin", "Compose", "Coroutines", "Flow", "Channel",
        "StateFlow", "SharedFlow", "LazyColumn", "LazyRow",
        "ViewModel", "Repository", "Room", "Retrofit", "Hilt",
        "Navigation", "Material", "Canvas", "Animation", "Modifier"
    )
    return allItems.filter { it.contains(query, ignoreCase = true) }
}

@OptIn(FlowPreview::class)
@Composable
fun S06E12Answer() {
    var query by remember { mutableStateOf("") }
    var results by remember { mutableStateOf<List<String>>(emptyList()) }
    var searchCount by remember { mutableStateOf(0) }

    // snapshotFlow converts Compose state reads into a Flow.
    // Combined with debounce, it prevents searching on every keystroke.
    LaunchedEffect(Unit) {
        snapshotFlow { query }
            .debounce(300L)                // wait 300ms after last keystroke
            .filter { it.length >= 2 }     // only search for 2+ characters
            .distinctUntilChanged()         // skip if query has not changed
            .collect { debouncedQuery ->
                searchCount++
                results = performSearch(debouncedQuery)
            }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Debounced Search:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search (type 2+ chars)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text(
            text = "Searches performed: $searchCount",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp)
        )

        if (results.isNotEmpty()) {
            Text(
                text = "Results (${results.size}):",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )
            results.forEach { result ->
                Text(
                    text = result,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        } else if (query.length >= 2) {
            Text(
                text = "No results found",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Text(
            text = "debounce(300ms) waits until you stop typing for 300ms before searching. " +
                "This prevents firing a search on every keystroke.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
