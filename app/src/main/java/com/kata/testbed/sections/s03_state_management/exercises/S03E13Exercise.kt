package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E13Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Immutable State")
        // TODO: Create a state holding a list of items
        // TODO: Demonstrate adding items by creating a NEW list (state = state + newItem)
        // TODO: Show a counter of how many items exist
        // TODO: Add a button to add items and a button to clear
        // TODO: Add a note explaining why new list copies trigger recomposition correctly
    }
}
