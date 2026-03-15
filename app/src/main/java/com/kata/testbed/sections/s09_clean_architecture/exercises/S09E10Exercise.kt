package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E10Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Input Validation")
        // TODO: Create a CreateUserUseCase that validates input before saving
        // TODO: Validate: name must be non-empty, email must contain '@'
        // TODO: Return validation errors as a sealed class
        // TODO: Display both valid and invalid submission results
    }
}
