package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Shared contract that all modules depend on (lives in a :core:navigation module)
private interface NavigationEntry {
    val route: String
    val label: String
    val content: @Composable (onNavigate: (String) -> Unit) -> Unit
}

// Module A registers its screens - depends only on the NavigationEntry contract
private class HomeModule : NavigationEntry {
    override val route = "home"
    override val label = "Home"
    override val content: @Composable (onNavigate: (String) -> Unit) -> Unit = { onNavigate ->
        Column {
            Text("Home Module", style = MaterialTheme.typography.headlineSmall)
            Text("This screen is provided by the :feature:home module.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { onNavigate("settings") }) {
                Text("Go to Settings")
            }
        }
    }
}

// Module B registers its screens - no dependency on Module A
private class SettingsModule : NavigationEntry {
    override val route = "settings"
    override val label = "Settings"
    override val content: @Composable (onNavigate: (String) -> Unit) -> Unit = { onNavigate ->
        Column {
            Text("Settings Module", style = MaterialTheme.typography.headlineSmall)
            Text("This screen is provided by the :feature:settings module.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { onNavigate("profile") }) {
                Text("Go to Profile")
            }
        }
    }
}

// Module C - another independent feature module
private class ProfileModule : NavigationEntry {
    override val route = "profile"
    override val label = "Profile"
    override val content: @Composable (onNavigate: (String) -> Unit) -> Unit = { _ ->
        Column {
            Text("Profile Module", style = MaterialTheme.typography.headlineSmall)
            Text("This screen is provided by the :feature:profile module.")
        }
    }
}

@Composable
fun S04E15Answer() {
    // App-level registry collects entries from all modules.
    // In a real app, modules register via Dagger/Hilt multibindings or a ServiceLoader.
    val registry = remember {
        listOf<NavigationEntry>(HomeModule(), SettingsModule(), ProfileModule())
    }
    var currentRoute by remember { mutableStateOf("home") }

    val currentEntry = registry.find { it.route == currentRoute }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Multi-Module Navigation", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Current: $currentRoute",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Module screens - each module provides its own content
        if (currentEntry != null) {
            currentEntry.content { route -> currentRoute = route }
        } else {
            Text("Unknown route: $currentRoute", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        // Registry overview
        Text("Registered modules:", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(4.dp))
        registry.forEach { entry ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .clickable { currentRoute = entry.route }
            ) {
                Text(
                    text = "${entry.label} (/${entry.route})",
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Multi-module navigation uses a shared contract interface. " +
                "Each feature module implements the interface and registers its routes. " +
                "The app module collects all entries without knowing module internals. " +
                "Modules navigate by route string, not by direct reference.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
