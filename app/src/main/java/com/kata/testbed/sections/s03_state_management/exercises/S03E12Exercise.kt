package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E12Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Recomposition Optimization")
        // TODO: Create a list of items with unique IDs
        // TODO: Add a Shuffle button that reorders the list
        // TODO: Use key(item.id) { ... } inside the list iteration
        // TODO: Display a recomposition counter to show the optimization effect
    }
}
