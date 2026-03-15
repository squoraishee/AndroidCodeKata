package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E08Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: ViewModel with Use Cases")
        // TODO: Create a UserListViewModel that depends on GetUsersUseCase (not repository)
        // TODO: ViewModel should expose a UI state
        // TODO: Load users via the use case and update state
        // TODO: Display the state
    }
}
