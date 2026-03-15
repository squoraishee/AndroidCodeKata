package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Shared state holder simulates a ViewModel shared between multiple composables.
// In real apps, use activityViewModels() or a scoped ViewModel.
private class SharedStateHolder {
    private val _sharedText = MutableStateFlow("Hello from shared state!")
    val sharedText: StateFlow<String> = _sharedText.asStateFlow()

    fun updateText(newText: String) {
        _sharedText.value = newText
    }
}

@Composable
private fun WriterChild(stateHolder: SharedStateHolder) {
    val text by stateHolder.sharedText.collectAsState()
    Column {
        Text("Writer (Child A):", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { stateHolder.updateText(it) },
            label = { Text("Edit shared text") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ReaderChild(stateHolder: SharedStateHolder) {
    val text by stateHolder.sharedText.collectAsState()
    Column {
        Text("Reader (Child B):", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Shared value: \"$text\"",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun S03E11Answer() {
    // Single instance shared between both children
    val stateHolder = remember { SharedStateHolder() }

    Column(modifier = Modifier.padding(16.dp)) {
        WriterChild(stateHolder)

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        ReaderChild(stateHolder)

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "A shared state holder (or ViewModel) lets sibling composables share state. " +
                "The parent creates the instance and passes it down. Changes in one child " +
                "are immediately visible in the other.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
