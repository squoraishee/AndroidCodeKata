package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

// A suspend function can call other suspend functions like delay().
// It can only be called from a coroutine or another suspend function.
private suspend fun fetchGreeting(): String {
    delay(1000L) // simulate network call
    return "Hello from a coroutine!"
}

@Composable
fun S06E01Answer() {
    var result by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // LaunchedEffect(Unit) runs once when the composable enters composition.
    // It provides a coroutine scope where suspend functions can be called.
    LaunchedEffect(Unit) {
        result = fetchGreeting()
        isLoading = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "First Suspend Function:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (isLoading) {
            CircularProgressIndicator()
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            Text(
                text = result ?: "No result",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "The suspend function delayed for 1 second before returning this value.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
