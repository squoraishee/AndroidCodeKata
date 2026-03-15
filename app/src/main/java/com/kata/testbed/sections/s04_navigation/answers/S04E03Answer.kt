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

// Type-safe routes: sealed interface hierarchy replaces string routes.
// In Compose Navigation 2.8+, these can be @Serializable route objects.
private sealed interface Route {
    data object Home : Route
    data class Detail(val id: Int, val title: String) : Route
    data class Settings(val section: String = "general") : Route
}

@Composable
fun S04E03Answer() {
    var currentRoute by remember { mutableStateOf<Route>(Route.Home) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Route: ${currentRoute::class.simpleName}",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))

        when (val route = currentRoute) {
            is Route.Home -> {
                Text("Home", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                // Type-safe: compiler ensures id is Int and title is String
                Button(onClick = {
                    currentRoute = Route.Detail(id = 42, title = "Kotlin Guide")
                }) {
                    Text("Go to Detail (id=42)")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Button(onClick = {
                    currentRoute = Route.Settings(section = "appearance")
                }) {
                    Text("Go to Settings (appearance)")
                }
            }
            is Route.Detail -> {
                Text("Detail", style = MaterialTheme.typography.headlineMedium)
                // Accessing route.id is compile-time safe - no string parsing needed
                Text("ID: ${route.id}")
                Text("Title: ${route.title}")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { currentRoute = Route.Home }) { Text("Back") }
            }
            is Route.Settings -> {
                Text("Settings", style = MaterialTheme.typography.headlineMedium)
                Text("Section: ${route.section}")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { currentRoute = Route.Home }) { Text("Back") }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Type-safe routes use data classes/objects instead of string paths. " +
                "Arguments are properties with proper types. " +
                "The compiler catches invalid arguments at build time, not runtime.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
