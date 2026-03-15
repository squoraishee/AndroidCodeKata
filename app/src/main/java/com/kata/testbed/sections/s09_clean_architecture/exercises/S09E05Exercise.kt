package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E05Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Parameterized Use Case")
        // TODO: Create a GetUserByIdUseCase that takes UserRepository in constructor
        // TODO: Add operator fun invoke(id: String): User?
        // TODO: Test with both existing and non-existing IDs
        // TODO: Display found user and "not found" case
    }
}
