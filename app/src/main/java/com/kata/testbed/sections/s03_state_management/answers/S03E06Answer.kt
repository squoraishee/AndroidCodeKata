package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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

// Simulates a ViewModel. In real apps, extend androidx.lifecycle.ViewModel.
// The key pattern: private MutableStateFlow, public StateFlow, methods to modify.
private class CounterStateHolder {
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
fun S03E06Answer() {
    val stateHolder = remember { CounterStateHolder() }

    // collectAsState() converts a StateFlow into Compose State,
    // triggering recomposition when the flow emits a new value.
    val count by stateHolder.count.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { stateHolder.increment() }) {
            Text("Increment")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { stateHolder.decrement() }) {
            Text("Decrement")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { stateHolder.reset() }) {
            Text("Reset")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "StateFlow in a ViewModel (or state holder) provides unidirectional data flow. " +
                "The UI observes state via collectAsState() and dispatches actions via methods.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
