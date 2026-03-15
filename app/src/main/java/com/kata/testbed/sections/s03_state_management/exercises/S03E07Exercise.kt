package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E07Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: UiState Sealed Class")
        // TODO: Define a sealed interface UiState with Loading, Success(data: List<String>), Error(message: String)
        // TODO: Create a state holding the current UiState, starting with Loading
        // TODO: Add buttons to simulate transitioning to Success or Error
        // TODO: Display different UI for each state using a when expression
    }
}
