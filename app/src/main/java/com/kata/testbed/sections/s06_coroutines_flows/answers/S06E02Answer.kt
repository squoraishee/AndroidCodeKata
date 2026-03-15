package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

@Composable
fun S06E02Answer() {
    var result1 by remember { mutableStateOf<String?>(null) }
    var result2 by remember { mutableStateOf<String?>(null) }
    var elapsedMs by remember { mutableLongStateOf(0L) }
    var isDone by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val startTime = System.currentTimeMillis()

        // async launches a coroutine and returns a Deferred<T>.
        // Both run concurrently -- the total time is the max, not the sum.
        val deferred1 = async {
            delay(1000L)
            "User data loaded"
        }
        val deferred2 = async {
            delay(1500L)
            "Settings data loaded"
        }

        // await() suspends until the result is ready
        result1 = deferred1.await()
        result2 = deferred2.await()
        elapsedMs = System.currentTimeMillis() - startTime
        isDone = true
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Launch and Async (Concurrent Tasks):",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (!isDone) {
            CircularProgressIndicator()
            Text(
                text = "Running two tasks concurrently...",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            Text(text = "Task 1: $result1", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Task 2: $result2",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Total time: ${elapsedMs}ms",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Sequential would take ~2500ms. Concurrent takes ~1500ms because both tasks run at the same time.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
