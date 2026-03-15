package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E12Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Conditional Navigation")
        // TODO: Create an isLoggedIn state, initially false
        // TODO: If not logged in, show a Login screen with a Login button
        // TODO: After login, show the protected Home screen
        // TODO: Add a Logout button on the Home screen that returns to Login
    }
}
