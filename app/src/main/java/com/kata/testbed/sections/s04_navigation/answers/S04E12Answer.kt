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

@Composable
fun S04E12Answer() {
    var isLoggedIn by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var welcomeName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Auth guard: redirect to login if not authenticated.
        // In real Navigation, use LaunchedEffect to navigate away from protected routes.
        if (!isLoggedIn) {
            Text("Login Required", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "You must log in to access this content.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    if (username.isNotBlank()) {
                        welcomeName = username
                        isLoggedIn = true
                    }
                },
                enabled = username.isNotBlank() && password.isNotBlank()
            ) {
                Text("Log In")
            }
        } else {
            // Protected content - only visible when authenticated
            Text("Protected Content", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Welcome back, $welcomeName!",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text("This is the secret content only logged-in users can see.")
            Text("Account settings, personal data, and more live here.")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                isLoggedIn = false
                username = ""
                password = ""
            }) {
                Text("Log Out")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Conditional navigation guards protected routes. " +
                "Check auth state before showing content. " +
                "In Navigation, use LaunchedEffect to redirect: " +
                "if (!isLoggedIn) navController.navigate(\"login\") { popUpTo(\"home\") }.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
