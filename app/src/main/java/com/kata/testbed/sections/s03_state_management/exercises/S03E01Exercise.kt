package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E01Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: remember and mutableStateOf")
        // TODO: Create a counter state with remember { mutableStateOf(0) }
        // TODO: Display the count with Text composable
        // TODO: Add a Button that increments the count when clicked
    }
}
