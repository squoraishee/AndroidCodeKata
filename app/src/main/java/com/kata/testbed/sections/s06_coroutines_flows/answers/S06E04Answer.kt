package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@Composable
fun S06E04Answer() {
    val log = remember { mutableListOf<String>().toMutableStateList() }

    LaunchedEffect(Unit) {
        log.add("--- Part 1: try/catch ---")

        // Basic try/catch works for exceptions inside the same coroutine
        try {
            delay(100)
            throw IllegalStateException("Something went wrong!")
        } catch (e: IllegalStateException) {
            log.add("Caught: ${e.message}")
        }

        log.add("")
        log.add("--- Part 2: supervisorScope ---")

        // supervisorScope prevents a failed child from cancelling siblings.
        // Without it, one child's failure would cancel the entire scope.
        supervisorScope {
            val handler = CoroutineExceptionHandler { _, throwable ->
                log.add("Handler caught: ${throwable.message}")
            }

            launch(handler) {
                delay(100)
                log.add("Task A: about to fail...")
                throw RuntimeException("Task A failed!")
            }

            launch {
                delay(300)
                // This completes because supervisorScope isolates failures
                log.add("Task B: completed successfully!")
            }

            launch {
                delay(500)
                log.add("Task C: also completed!")
            }
        }

        log.add("")
        log.add("supervisorScope finished. Tasks B and C survived Task A's failure.")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Exception Handling in Coroutines:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        log.forEach { entry ->
            if (entry.isEmpty()) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            } else {
                Text(
                    text = entry,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}
