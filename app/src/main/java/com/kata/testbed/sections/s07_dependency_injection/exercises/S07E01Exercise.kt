package com.kata.testbed.sections.s07_dependency_injection.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S07E01Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a UserRepository interface with a fun getUser(id: Int): String
        // TODO: Create an InMemoryUserRepository implementing UserRepository
        // TODO: Create a UserService class that takes UserRepository via constructor
        // TODO: Instantiate the service with the repository and display the result
    }
}
