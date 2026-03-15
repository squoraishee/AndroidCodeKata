package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Two separate nav graphs (auth and main), each with their own screens
private sealed interface AuthScreen {
    data object Login : AuthScreen
    data object Register : AuthScreen
}

private sealed interface MainScreen {
    data object Home : MainScreen
    data object Settings : MainScreen
}

private sealed interface AppFlow {
    data class Auth(val screen: AuthScreen = AuthScreen.Login) : AppFlow
    data class Main(val screen: MainScreen = MainScreen.Home) : AppFlow
}

@Composable
fun S04E05Answer() {
    var flow by remember { mutableStateOf<AppFlow>(AppFlow.Auth()) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Flow indicator
        val flowLabel = when (flow) {
            is AppFlow.Auth -> "Auth Flow"
            is AppFlow.Main -> "Main Flow"
        }
        Text("Graph: $flowLabel", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        when (val currentFlow = flow) {
            is AppFlow.Auth -> {
                when (currentFlow.screen) {
                    is AuthScreen.Login -> {
                        Text("Login", style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        var username by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = { Text("Username") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { flow = AppFlow.Main() }) {
                            Text("Log In")
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedButton(onClick = {
                            flow = AppFlow.Auth(AuthScreen.Register)
                        }) {
                            Text("Create Account")
                        }
                    }
                    is AuthScreen.Register -> {
                        Text("Register", style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Create your new account here.")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { flow = AppFlow.Main() }) {
                            Text("Sign Up")
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedButton(onClick = {
                            flow = AppFlow.Auth(AuthScreen.Login)
                        }) {
                            Text("Back to Login")
                        }
                    }
                }
            }
            is AppFlow.Main -> {
                when (currentFlow.screen) {
                    is MainScreen.Home -> {
                        Text("Home", style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("You are logged in!")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            flow = AppFlow.Main(MainScreen.Settings)
                        }) {
                            Text("Settings")
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedButton(onClick = { flow = AppFlow.Auth() }) {
                            Text("Log Out")
                        }
                    }
                    is MainScreen.Settings -> {
                        Text("Settings", style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("App settings go here.")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            flow = AppFlow.Main(MainScreen.Home)
                        }) {
                            Text("Back to Home")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Nested nav graphs separate concerns. The auth graph handles login/register. " +
                "The main graph handles app screens. On login, the entire auth graph is replaced with the main graph.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
