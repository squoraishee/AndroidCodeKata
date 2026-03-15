package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private sealed interface DeepLinkScreen {
    data object Home : DeepLinkScreen
    data class ItemDetail(val id: String) : DeepLinkScreen
    data object Profile : DeepLinkScreen
    data class Error(val path: String) : DeepLinkScreen
}

// Simulates deep link resolution - matches a URI path to a screen
private fun resolveDeepLink(path: String): DeepLinkScreen {
    val trimmed = path.trim().removePrefix("/")
    return when {
        trimmed.isEmpty() || trimmed == "home" -> DeepLinkScreen.Home
        trimmed == "profile" -> DeepLinkScreen.Profile
        trimmed.startsWith("item/") -> {
            val id = trimmed.removePrefix("item/")
            if (id.isNotBlank()) DeepLinkScreen.ItemDetail(id)
            else DeepLinkScreen.Error(path)
        }
        else -> DeepLinkScreen.Error(path)
    }
}

@Composable
fun S04E06Answer() {
    var deepLinkInput by remember { mutableStateOf("/item/42") }
    var currentScreen by remember { mutableStateOf<DeepLinkScreen>(DeepLinkScreen.Home) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Deep Link Resolver", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = deepLinkInput,
            onValueChange = { deepLinkInput = it },
            label = { Text("Deep link path") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("/item/42, /profile, /home") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { currentScreen = resolveDeepLink(deepLinkInput) }) {
            Text("Navigate")
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        when (val screen = currentScreen) {
            is DeepLinkScreen.Home -> {
                Text("Home Screen", style = MaterialTheme.typography.headlineSmall)
                Text("The default landing page.")
            }
            is DeepLinkScreen.ItemDetail -> {
                Text("Item Detail", style = MaterialTheme.typography.headlineSmall)
                Text("Viewing item ID: ${screen.id}")
                Text("Deep link resolved to item detail.")
            }
            is DeepLinkScreen.Profile -> {
                Text("Profile", style = MaterialTheme.typography.headlineSmall)
                Text("Your user profile.")
            }
            is DeepLinkScreen.Error -> {
                Text(
                    "Unknown route: ${screen.path}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error
                )
                Text("Try: /home, /profile, or /item/{id}")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { currentScreen = DeepLinkScreen.Home }) {
            Text("Go Home")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Deep links map URIs to app screens. In Compose Navigation, " +
                "add deepLinks to composable() destinations. The system handles " +
                "intents with matching URIs automatically.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
