package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E05Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Multiple State Fields")
        // TODO: Create state variables for name and email using remember { mutableStateOf("") }
        // TODO: Create a submitted state with remember { mutableStateOf(false) }
        // TODO: Add OutlinedTextFields for name and email
        // TODO: Add a Button enabled only when both fields are non-empty
        // TODO: On submit, show a greeting message
    }
}
