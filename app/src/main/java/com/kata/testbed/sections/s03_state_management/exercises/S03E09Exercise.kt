package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E09Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Snapshot Flow")
        // TODO: Create a text input state with remember { mutableStateOf("") }
        // TODO: Use snapshotFlow { textState } inside a LaunchedEffect
        // TODO: Apply debounce(300) to the flow
        // TODO: Collect the debounced value and store it in another state
        // TODO: Display both the raw input and the debounced value
    }
}
