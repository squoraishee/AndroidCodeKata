package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E01Answer() {
    // remember preserves state across recompositions.
    // mutableStateOf creates an observable state that triggers recomposition when changed.
    var count by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { count++ }) {
            Text("Increment")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { count = 0 }) {
            Text("Reset")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "remember keeps the value alive across recompositions. " +
                "mutableStateOf makes it observable so Compose knows when to recompose.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
