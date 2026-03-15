package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E11Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Shared ViewModel")
        // TODO: Create a SharedStateHolder class with a MutableStateFlow<String>
        // TODO: Remember a single instance in the parent composable
        // TODO: Pass the state holder to two child composables
        // TODO: Child A has a text field that writes to the shared state
        // TODO: Child B displays the current value from the shared state
    }
}
