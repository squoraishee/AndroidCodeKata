package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Sealed interface represents all possible UI states.
// Each variant carries exactly the data needed for that state.
private sealed interface UiState {
    data object Loading : UiState
    data class Success(val items: List<String>) : UiState
    data class Error(val message: String) : UiState
}

@Composable
fun S03E07Answer() {
    var uiState by remember { mutableStateOf<UiState>(UiState.Loading) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Exhaustive when expression ensures every state is handled
        when (val state = uiState) {
            is UiState.Loading -> {
                Row {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Loading...", style = MaterialTheme.typography.bodyLarge)
                }
            }
            is UiState.Success -> {
                Text("Loaded ${state.items.size} items:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                state.items.forEach { item ->
                    Text(text = "- $item", modifier = Modifier.padding(vertical = 1.dp))
                }
            }
            is UiState.Error -> {
                Text(
                    text = "Error: ${state.message}",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Simulate state transitions:", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { uiState = UiState.Loading }) {
            Text("Loading")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = {
            uiState = UiState.Success(listOf("Kotlin", "Compose", "Coroutines"))
        }) {
            Text("Success")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { uiState = UiState.Error("Network timeout") }) {
            Text("Error")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Sealed interfaces model all possible states exhaustively. " +
                "The when expression forces handling every variant at compile time.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
