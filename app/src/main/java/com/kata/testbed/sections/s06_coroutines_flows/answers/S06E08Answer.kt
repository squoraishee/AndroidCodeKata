package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// In production, this would be a ViewModel.
// StateFlow always has a current value and replays the latest value to new collectors.
private class CounterHolder {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    fun increment() {
        _count.value++
    }

    fun decrement() {
        _count.value--
    }

    fun reset() {
        _count.value = 0
    }
}

@Composable
fun S06E08Answer() {
    val counter = remember { CounterHolder() }
    // collectAsState() converts a StateFlow into Compose State,
    // triggering recomposition whenever the flow emits a new value.
    val count by counter.count.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "StateFlow Counter:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "$count",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { counter.decrement() }) {
                Text("-")
            }
            Button(onClick = { counter.reset() }) {
                Text("Reset")
            }
            Button(onClick = { counter.increment() }) {
                Text("+")
            }
        }

        Text(
            text = "The count is backed by MutableStateFlow. " +
                "collectAsState() bridges Flow into Compose's state system. " +
                "In a real app, CounterHolder would be a ViewModel.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
