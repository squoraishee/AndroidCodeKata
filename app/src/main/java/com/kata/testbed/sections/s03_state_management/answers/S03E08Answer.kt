package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun S03E08Answer() {
    var message by remember { mutableStateOf("") }
    var notification by remember { mutableStateOf<String?>(null) }
    // Using a counter as a key to re-trigger LaunchedEffect for repeated events
    var eventKey by remember { mutableIntStateOf(0) }

    // LaunchedEffect restarts when eventKey changes.
    // This ensures the notification auto-dismisses after a delay.
    if (notification != null) {
        LaunchedEffect(eventKey) {
            delay(2000)
            notification = null
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Enter message") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (message.isNotBlank()) {
                    notification = "Submitted: $message"
                    eventKey++ // Increment key to re-trigger the dismiss timer
                    message = ""
                }
            }
        ) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // One-shot notification that auto-dismisses
        AnimatedVisibility(
            visible = notification != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = notification ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(12.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "One-shot events (snackbars, toasts, navigation) should not be part of persistent state. " +
                "Use a key-based LaunchedEffect to auto-dismiss, so the event does not replay on recomposition.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
