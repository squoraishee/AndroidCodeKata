package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@OptIn(FlowPreview::class)
@Composable
fun S03E09Answer() {
    var rawText by remember { mutableStateOf("") }
    var debouncedText by remember { mutableStateOf("") }

    // snapshotFlow converts a Compose State read into a cold Flow.
    // Each time rawText changes, the flow emits a new value.
    // We apply debounce to only act after the user pauses typing.
    LaunchedEffect(Unit) {
        snapshotFlow { rawText }
            .debounce(500L)
            .collect { debouncedText = it }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = rawText,
            onValueChange = { rawText = it },
            label = { Text("Type something (debounced)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Raw: \"$rawText\"",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Debounced (500ms): \"$debouncedText\"",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "snapshotFlow bridges Compose state into the coroutines Flow world. " +
                "This lets you apply operators like debounce, distinctUntilChanged, or map " +
                "to Compose state changes.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
