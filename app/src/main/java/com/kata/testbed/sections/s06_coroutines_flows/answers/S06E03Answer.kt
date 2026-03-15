package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun S06E03Answer() {
    val eventLog = remember { mutableListOf<String>().toMutableStateList() }

    LaunchedEffect(Unit) {
        eventLog.add("Starting structured concurrency demo...")

        // coroutineScope creates a new scope and suspends until ALL children complete.
        // If any child fails, all other children are cancelled (structured concurrency).
        coroutineScope {
            eventLog.add("Parent: launched scope, starting children...")

            launch {
                delay(500L)
                eventLog.add("Child 1: done after 500ms")
            }

            launch {
                delay(1000L)
                eventLog.add("Child 2: done after 1000ms")
            }

            launch {
                delay(750L)
                eventLog.add("Child 3: done after 750ms")
            }

            // This line executes immediately -- launch is non-blocking
            eventLog.add("Parent: all children launched, now waiting...")
        }

        // This line runs only after ALL children have completed
        eventLog.add("All children completed! Scope finished.")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Structured Concurrency:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Event Log:",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        eventLog.forEachIndexed { index, event ->
            Text(
                text = "${index + 1}. $event",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}
