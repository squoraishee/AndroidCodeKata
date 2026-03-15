package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E02Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Repository Interface")
        // TODO: Define a UserRepository interface with:
        //   fun getAll(): List<User>
        //   fun getById(id: String): User?
        //   fun save(user: User)
        //   fun delete(id: String)
        // TODO: Display the interface contract as text
    }
}
