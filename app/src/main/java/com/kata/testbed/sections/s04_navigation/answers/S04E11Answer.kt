package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private sealed interface BackHandlerScreen {
    data object Home : BackHandlerScreen
    data object Editor : BackHandlerScreen
}

@Composable
fun S04E11Answer() {
    var screen by remember { mutableStateOf<BackHandlerScreen>(BackHandlerScreen.Home) }
    var editorText by remember { mutableStateOf("") }
    var showConfirmDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        when (screen) {
            is BackHandlerScreen.Home -> {
                Text("Home", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("No unsaved changes here.")
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    editorText = ""
                    screen = BackHandlerScreen.Editor
                }) {
                    Text("Open Editor")
                }
            }
            is BackHandlerScreen.Editor -> {
                Text("Editor", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = editorText,
                    onValueChange = { editorText = it },
                    label = { Text("Edit content") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
                Spacer(modifier = Modifier.height(8.dp))

                val hasChanges = editorText.isNotBlank()
                Text(
                    text = if (hasChanges) "Unsaved changes detected" else "No changes",
                    color = if (hasChanges) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    // Simulated back button - in real apps, use BackHandler composable
                    OutlinedButton(onClick = {
                        if (hasChanges) {
                            showConfirmDialog = true
                        } else {
                            screen = BackHandlerScreen.Home
                        }
                    }) {
                        Text("Back")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        // "Save" and go back
                        screen = BackHandlerScreen.Home
                    }) {
                        Text("Save & Close")
                    }
                }
            }
        }

        // Confirmation dialog
        if (showConfirmDialog) {
            AlertDialog(
                onDismissRequest = { showConfirmDialog = false },
                title = { Text("Unsaved Changes") },
                text = { Text("You have unsaved changes. Discard them?") },
                confirmButton = {
                    TextButton(onClick = {
                        showConfirmDialog = false
                        editorText = ""
                        screen = BackHandlerScreen.Home
                    }) {
                        Text("Discard")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showConfirmDialog = false }) {
                        Text("Stay")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "BackHandler { } intercepts the system back button in Compose. " +
                "Use it to show confirmation dialogs when there are unsaved changes. " +
                "The enabled parameter controls when the handler is active.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
