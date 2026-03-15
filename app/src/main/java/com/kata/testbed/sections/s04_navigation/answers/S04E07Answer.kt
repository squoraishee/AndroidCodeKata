package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private sealed interface ResultScreen {
    data class ScreenA(val selectedColor: String?) : ResultScreen
    data object ScreenB : ResultScreen
}

@Composable
fun S04E07Answer() {
    // The result is stored in the parent state, simulating savedStateHandle.
    // In real Navigation: navController.previousBackStackEntry?.savedStateHandle?.set("color", value)
    var screen by remember { mutableStateOf<ResultScreen>(ResultScreen.ScreenA(null)) }

    Column(modifier = Modifier.padding(16.dp)) {
        when (val current = screen) {
            is ResultScreen.ScreenA -> {
                Text("Screen A", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Selected color: ${current.selectedColor ?: "None"}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { screen = ResultScreen.ScreenB }) {
                    Text("Select a Color")
                }
            }
            is ResultScreen.ScreenB -> {
                Text("Screen B - Pick a Color", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))

                val colors = listOf(
                    "Red" to Color(0xFFE57373),
                    "Blue" to Color(0xFF64B5F6),
                    "Green" to Color(0xFF81C784),
                    "Purple" to Color(0xFFBA68C8),
                    "Orange" to Color(0xFFFFB74D)
                )
                colors.forEach { (name, color) ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = color),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                // Return result to Screen A
                                screen = ResultScreen.ScreenA(selectedColor = name)
                            }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier.padding(16.dp),
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { screen = ResultScreen.ScreenA(null) }) {
                    Text("Cancel")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Navigation results pass data backward. " +
                "In Compose Navigation, use savedStateHandle on the previous back stack entry " +
                "to set a result, and observe it with getLiveData() or getStateFlow().",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
