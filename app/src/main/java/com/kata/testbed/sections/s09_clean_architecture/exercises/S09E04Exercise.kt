package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E04Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Simple Use Case")
        // TODO: Create a GetUsersUseCase class that takes UserRepository in constructor
        // TODO: Add operator fun invoke(): List<User> that delegates to repository.getAll()
        // TODO: Instantiate the use case with a repository and call it
        // TODO: Display the results
    }
}
