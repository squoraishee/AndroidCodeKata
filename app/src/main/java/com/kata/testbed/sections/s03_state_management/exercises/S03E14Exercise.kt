package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E14Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: State Machine")
        // TODO: Define a sealed interface FormState with: Input, Review, Confirm, Done
        // TODO: Hold the current state with remember { mutableStateOf<FormState>(FormState.Input) }
        // TODO: Show different UI for each state
        // TODO: Provide Next/Back buttons that perform valid state transitions only
        // TODO: Collect form data in Input, display it in Review, confirm in Confirm, show success in Done
    }
}
