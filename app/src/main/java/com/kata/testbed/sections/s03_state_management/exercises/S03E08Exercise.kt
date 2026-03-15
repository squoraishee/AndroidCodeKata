package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E08Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: One-Shot Events")
        // TODO: Create a text field for a message
        // TODO: Create a state for showing a notification (one-shot event)
        // TODO: When the user clicks Submit, show a temporary notification
        // TODO: The notification should auto-dismiss (use LaunchedEffect with a delay)
        // TODO: The notification should not reappear on recomposition
    }
}
