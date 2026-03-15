package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// flow { } builder creates a cold Flow.
// "Cold" means it does not emit until someone collects it.
// Each collector gets its own independent emission sequence.
private fun numberFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(500L)
        emit(i) // send the value downstream to the collector
    }
}

@Composable
fun S06E06Answer() {
    val collectedValues = remember { mutableListOf<Int>().toMutableStateList() }
    var isCollecting by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // collect is a terminal operator that triggers emission.
        // It suspends until the flow completes.
        numberFlow().collect { value ->
            collectedValues.add(value)
        }
        isCollecting = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Flow Basics:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (isCollecting) {
            Text(
                text = "Collecting values...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        } else {
            Text(
                text = "Flow completed!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = "Received values:",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )

        collectedValues.forEach { value ->
            Text(
                text = "Emitted: $value",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }

        if (collectedValues.isEmpty()) {
            Text(
                text = "Waiting for first emission...",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
