package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Simulated event listener to demonstrate register/unregister pattern
private class SensorListener(private val onEvent: (String) -> Unit) {
    fun register() {
        onEvent("Listener REGISTERED")
    }

    fun unregister() {
        onEvent("Listener UNREGISTERED")
    }

    fun simulateEvent() {
        onEvent("Sensor event received!")
    }
}

@Composable
private fun SensorListenerChild(onLog: (String) -> Unit) {
    val listener = remember { SensorListener(onLog) }

    // DisposableEffect runs the block on composition enter.
    // onDispose runs when the composable leaves composition.
    // This guarantees cleanup even if the composable is removed unexpectedly.
    DisposableEffect(Unit) {
        listener.register()
        onDispose {
            listener.unregister()
        }
    }

    Column {
        Text(
            "Sensor listener is ACTIVE",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { listener.simulateEvent() }) {
            Text("Simulate Sensor Event")
        }
    }
}

@Composable
fun S04E09Answer() {
    var showListener by remember { mutableStateOf(false) }
    var log by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("DisposableEffect Demo", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Toggle shows/hides the child, triggering DisposableEffect enter/exit
        Text("Show sensor listener:")
        Switch(
            checked = showListener,
            onCheckedChange = { showListener = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (showListener) {
            SensorListenerChild(onLog = { log = log + it })
        } else {
            Text(
                "Listener not shown. Toggle on to register.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))
        Text("Event Log:", style = MaterialTheme.typography.titleSmall)
        Button(onClick = { log = emptyList() }) { Text("Clear") }
        Spacer(modifier = Modifier.height(4.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            log.forEachIndexed { index, entry ->
                Text("${index + 1}. $entry", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "DisposableEffect is for setup/teardown of resources tied to composition lifetime. " +
                "The onDispose block guarantees cleanup. " +
                "Common uses: registering listeners, starting/stopping observers, binding services.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
