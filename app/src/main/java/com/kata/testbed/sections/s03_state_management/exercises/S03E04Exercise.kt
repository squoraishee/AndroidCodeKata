package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E04Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Derived State")
        // TODO: Create a list of fruit names with remember
        // TODO: Create a search query state with remember { mutableStateOf("") }
        // TODO: Use derivedStateOf to compute a filtered list based on the query
        // TODO: Display an OutlinedTextField for search and the filtered list below
    }
}
