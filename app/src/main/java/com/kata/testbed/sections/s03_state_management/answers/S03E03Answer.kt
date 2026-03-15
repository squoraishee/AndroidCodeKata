package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E03Answer() {
    // rememberSaveable survives configuration changes (rotation, process death)
    // by saving state to the Bundle. Works with primitive types and Parcelable.
    var savedText by rememberSaveable { mutableStateOf("") }

    // Regular remember does NOT survive config changes - shown for comparison
    var unsavedText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("rememberSaveable (survives config change):", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = savedText,
            onValueChange = { savedText = it },
            label = { Text("Saved text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Regular remember (lost on config change):", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = unsavedText,
            onValueChange = { unsavedText = it },
            label = { Text("Unsaved text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "rememberSaveable stores state in the saved instance state Bundle. " +
                "It works automatically for primitives, strings, and Parcelable types.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
