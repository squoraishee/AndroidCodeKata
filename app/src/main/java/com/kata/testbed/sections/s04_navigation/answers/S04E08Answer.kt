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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun S04E08Answer() {
    var events by remember { mutableStateOf(listOf<String>()) }
    val lifecycleOwner = LocalLifecycleOwner.current

    // DisposableEffect registers the observer when the composable enters composition
    // and removes it in onDispose when the composable leaves.
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            val eventName = when (event) {
                Lifecycle.Event.ON_CREATE -> "ON_CREATE"
                Lifecycle.Event.ON_START -> "ON_START"
                Lifecycle.Event.ON_RESUME -> "ON_RESUME"
                Lifecycle.Event.ON_PAUSE -> "ON_PAUSE"
                Lifecycle.Event.ON_STOP -> "ON_STOP"
                Lifecycle.Event.ON_DESTROY -> "ON_DESTROY"
                Lifecycle.Event.ON_ANY -> "ON_ANY"
            }
            events = events + eventName
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            // Note: we can't update state here since the composable is leaving
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Lifecycle Events", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Observing via LocalLifecycleOwner",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { events = emptyList() }) {
            Text("Clear Log")
        }

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        if (events.isEmpty()) {
            Text(
                "No events yet. Minimize/restore the app to see lifecycle events.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                events.forEachIndexed { index, event ->
                    Text(
                        text = "${index + 1}. $event",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "LifecycleEventObserver watches lifecycle transitions. " +
                "Use LocalLifecycleOwner.current to access the lifecycle from any composable. " +
                "Always clean up observers in onDispose.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
