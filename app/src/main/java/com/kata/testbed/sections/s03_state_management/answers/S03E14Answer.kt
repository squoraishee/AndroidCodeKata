package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

// Each state carries its own data. Transitions are explicit and validated.
private sealed interface FormState {
    data object Input : FormState
    data class Review(val name: String, val email: String) : FormState
    data class Confirm(val name: String, val email: String) : FormState
    data class Done(val name: String) : FormState
}

@Composable
fun S03E14Answer() {
    var formState by remember { mutableStateOf<FormState>(FormState.Input) }
    var nameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // State indicator
        val stateLabel = when (formState) {
            is FormState.Input -> "Step 1: Input"
            is FormState.Review -> "Step 2: Review"
            is FormState.Confirm -> "Step 3: Confirm"
            is FormState.Done -> "Complete"
        }
        Text(text = stateLabel, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        when (val state = formState) {
            is FormState.Input -> {
                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = emailInput,
                    onValueChange = { emailInput = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { formState = FormState.Review(nameInput, emailInput) },
                    enabled = nameInput.isNotBlank() && emailInput.isNotBlank()
                ) {
                    Text("Next: Review")
                }
            }

            is FormState.Review -> {
                Text("Name: ${state.name}")
                Text("Email: ${state.email}")
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    OutlinedButton(onClick = { formState = FormState.Input }) {
                        Text("Back")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { formState = FormState.Confirm(state.name, state.email) }) {
                        Text("Next: Confirm")
                    }
                }
            }

            is FormState.Confirm -> {
                Text("Please confirm your submission:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Name: ${state.name}")
                Text("Email: ${state.email}")
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    OutlinedButton(onClick = {
                        formState = FormState.Review(state.name, state.email)
                    }) {
                        Text("Back")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { formState = FormState.Done(state.name) }) {
                        Text("Submit")
                    }
                }
            }

            is FormState.Done -> {
                Text(
                    text = "Thank you, ${state.name}!",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Your form has been submitted.")
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    nameInput = ""
                    emailInput = ""
                    formState = FormState.Input
                }) {
                    Text("Start Over")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "A state machine enforces valid transitions. Each sealed class variant " +
                "carries exactly the data it needs. Invalid transitions (like jumping from " +
                "Input to Done) are impossible by design.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
