package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
fun S03E05Answer() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    // The button's enabled state depends on multiple fields
    val isFormValid = name.isNotBlank() && email.isNotBlank()

    Column(modifier = Modifier.padding(16.dp)) {
        if (!submitted) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { submitted = true },
                enabled = isFormValid
            ) {
                Text("Submit")
            }
            if (!isFormValid) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Fill in both fields to enable submit",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        } else {
            Text(
                text = "Hello, $name!",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Email: $email")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                submitted = false
                name = ""
                email = ""
            }) {
                Text("Reset")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Multiple state fields can be interdependent. " +
                "Derive computed values (like isFormValid) from the source states.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
