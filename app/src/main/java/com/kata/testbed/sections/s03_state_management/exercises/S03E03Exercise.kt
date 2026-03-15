package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E03Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: rememberSaveable")
        // TODO: Create a text state using rememberSaveable { mutableStateOf("") }
        // TODO: Add an OutlinedTextField bound to the text state
        // TODO: Display a message explaining that this value survives config changes
    }
}
