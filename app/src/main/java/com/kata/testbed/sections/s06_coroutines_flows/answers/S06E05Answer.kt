package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun S06E05Answer() {
    var count by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf("Not started") }
    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Cooperative Cancellation:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Status: $statusMessage",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Button(
                onClick = {
                    if (!isRunning) {
                        isRunning = true
                        statusMessage = "Running..."
                        job = scope.launch {
                            // isActive is a coroutine property that becomes false on cancellation.
                            // Checking it makes the coroutine "cooperative" --
                            // without it, cancellation could not stop the loop.
                            while (isActive) {
                                delay(500L)
                                count++
                            }
                            // This code runs after cancellation
                            statusMessage = "Cancelled at count $count"
                            isRunning = false
                        }
                    }
                },
                enabled = !isRunning
            ) {
                Text("Start")
            }

            Button(
                onClick = {
                    // cancel() sets isActive = false and throws CancellationException
                    // at the next suspension point (delay, yield, etc.)
                    job?.cancel()
                    statusMessage = "Cancelling..."
                },
                enabled = isRunning
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {
                    count = 0
                    statusMessage = "Reset"
                },
                enabled = !isRunning
            ) {
                Text("Reset")
            }
        }

        Text(
            text = "The coroutine checks isActive in its while loop. " +
                "When cancelled, it exits the loop cooperatively rather than being forcibly stopped.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
