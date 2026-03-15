package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E01Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Domain Model")
        // TODO: Create a User data class with id: String, name: String, email: String
        // TODO: The class should have NO Android dependencies - pure Kotlin only
        // TODO: Create an instance and display its properties using Text composables
    }
}
