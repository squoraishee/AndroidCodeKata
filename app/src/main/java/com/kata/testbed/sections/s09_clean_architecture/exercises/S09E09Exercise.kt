package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E09Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Error Propagation")
        // TODO: Define sealed class DomainError with NotFound, Unauthorized, NetworkError
        // TODO: Create a repository that can return errors
        // TODO: Propagate errors through use case to UI state
        // TODO: Display different UI for each error type
    }
}
