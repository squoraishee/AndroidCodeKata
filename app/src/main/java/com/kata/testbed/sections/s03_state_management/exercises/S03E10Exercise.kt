package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E10Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Custom Saver")
        // TODO: Define a data class UserProfile(val name: String, val age: Int, val city: String)
        // TODO: Create a custom Saver using mapSaver that saves/restores UserProfile
        // TODO: Use rememberSaveable(saver = ...) to hold the profile
        // TODO: Add text fields to edit each property
        // TODO: Display the current profile state
    }
}
