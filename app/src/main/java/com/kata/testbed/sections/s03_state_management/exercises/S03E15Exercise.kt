package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E15Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: MVI Pattern")
        // TODO: Define a data class TodoState(val items: List<String>, val input: String)
        // TODO: Define a sealed interface TodoIntent with: AddItem, RemoveItem(index), UpdateInput(text)
        // TODO: Create a reduce function: (TodoState, TodoIntent) -> TodoState
        // TODO: Hold state with remember, dispatch intents via the reduce function
        // TODO: Build UI that renders from state and dispatches intents on user actions
    }
}
