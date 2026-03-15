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
import androidx.compose.material3.IconButton
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

// MVI: Model (state), View (composable), Intent (user actions)

// Model - the single source of truth for the UI
private data class TodoModel(
    val items: List<String> = emptyList(),
    val input: String = ""
)

// Intent - all possible user actions
private sealed interface TodoIntent {
    data class UpdateInput(val text: String) : TodoIntent
    data object AddItem : TodoIntent
    data class RemoveItem(val index: Int) : TodoIntent
    data object ClearAll : TodoIntent
}

// Reducer - pure function that produces new state from current state + intent
private fun reduce(model: TodoModel, intent: TodoIntent): TodoModel {
    return when (intent) {
        is TodoIntent.UpdateInput -> model.copy(input = intent.text)
        is TodoIntent.AddItem -> {
            if (model.input.isBlank()) model
            else model.copy(
                items = model.items + model.input,
                input = ""
            )
        }
        is TodoIntent.RemoveItem -> model.copy(
            items = model.items.filterIndexed { i, _ -> i != intent.index }
        )
        is TodoIntent.ClearAll -> model.copy(items = emptyList())
    }
}

@Composable
fun S03E15Answer() {
    var model by remember { mutableStateOf(TodoModel()) }

    // dispatch sends an intent through the reducer to produce new state
    val dispatch: (TodoIntent) -> Unit = { intent -> model = reduce(model, intent) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("MVI Todo List", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = model.input,
                onValueChange = { dispatch(TodoIntent.UpdateInput(it)) },
                label = { Text("New item") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { dispatch(TodoIntent.AddItem) },
                enabled = model.input.isNotBlank()
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (model.items.isEmpty()) {
            Text(
                text = "No items yet. Add some!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            Text("${model.items.size} items:", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            model.items.forEachIndexed { index, item ->
                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                    Text(
                        text = item,
                        modifier = Modifier.weight(1f).padding(vertical = 4.dp)
                    )
                    IconButton(onClick = { dispatch(TodoIntent.RemoveItem(index)) }) {
                        Text("X", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = { dispatch(TodoIntent.ClearAll) }) {
                Text("Clear All")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "MVI (Model-View-Intent): The View renders from a single Model. " +
                "User actions are Intents. A pure reduce function computes new state. " +
                "This creates a predictable, testable unidirectional data flow.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
