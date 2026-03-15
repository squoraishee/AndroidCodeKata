package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@Composable
fun S06E09Answer() {
    // SharedFlow has no current value (unlike StateFlow).
    // replay = 0 means late subscribers miss past events -- ideal for one-shot events.
    val eventFlow = remember { MutableSharedFlow<String>() }
    val receivedEvents = remember { mutableListOf<String>().toMutableStateList() }
    val scope = rememberCoroutineScope()

    // Collect events as they arrive
    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            receivedEvents.add(event)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "SharedFlow Events:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Button(onClick = {
                scope.launch { eventFlow.emit("Info: Operation completed") }
            }) {
                Text("Info", style = MaterialTheme.typography.labelSmall)
            }
            Button(onClick = {
                scope.launch { eventFlow.emit("Warning: Low memory") }
            }) {
                Text("Warn", style = MaterialTheme.typography.labelSmall)
            }
            Button(onClick = {
                scope.launch { eventFlow.emit("Error: Connection lost") }
            }) {
                Text("Error", style = MaterialTheme.typography.labelSmall)
            }
        }

        Text(
            text = "Received Events (${receivedEvents.size}):",
            style = MaterialTheme.typography.titleSmall
        )

        LazyColumn(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            items(receivedEvents) { event ->
                Text(
                    text = event,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 2.dp),
                    color = when {
                        event.startsWith("Error") -> MaterialTheme.colorScheme.error
                        event.startsWith("Warning") -> MaterialTheme.colorScheme.tertiary
                        else -> MaterialTheme.colorScheme.primary
                    }
                )
            }
        }

        Text(
            text = "SharedFlow does not replay past events (replay = 0). " +
                "New collectors only see future emissions. " +
                "Use it for one-shot UI events like snackbars or navigation.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
