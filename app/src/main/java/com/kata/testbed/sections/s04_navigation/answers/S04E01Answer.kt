package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Simulated screens - in a real app, these would be NavHost destinations
private sealed interface Screen {
    data object Home : Screen
    data object Detail : Screen
}

@Composable
fun S04E01Answer() {
    // State-based navigation simulates how NavHost tracks the current destination.
    // In a real app, navController.navigate("detail") handles this.
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Current: ${currentScreen::class.simpleName}",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))

        when (currentScreen) {
            is Screen.Home -> {
                Text("Home Screen", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Welcome to the app!")
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { currentScreen = Screen.Detail }) {
                    Text("Go to Detail")
                }
            }
            is Screen.Detail -> {
                Text("Detail Screen", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Here are the details.")
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { currentScreen = Screen.Home }) {
                    Text("Go Back")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "NavHost maps routes to composable destinations. " +
                "navigate() pushes a new destination, popBackStack() returns. " +
                "Here we simulate this with state-driven screen switching.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
