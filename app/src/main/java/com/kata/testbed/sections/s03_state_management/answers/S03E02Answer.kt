package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// State hoisting: the child composable is stateless.
// It receives the current value and a callback to request changes.
@Composable
private fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onIncrement) {
            Text("Increment")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = onDecrement) {
            Text("Decrement")
        }
    }
}

@Composable
fun S03E02Answer() {
    // The parent owns the state and hoists it above the child.
    // This makes StatelessCounter reusable and testable.
    var count by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Parent owns the state:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        StatelessCounter(
            count = count,
            onIncrement = { count++ },
            onDecrement = { if (count > 0) count-- }
        )

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "State hoisting moves state up to the caller. " +
                "The child composable becomes stateless, reusable, and easier to test.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
