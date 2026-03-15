package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E03Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Repository Implementation")
        // TODO: Create an InMemoryUserRepository class that implements UserRepository
        // TODO: Use a mutableListOf<User>() as the backing store
        // TODO: Implement all CRUD methods
        // TODO: Demonstrate save, getAll, getById, and delete operations
    }
}
