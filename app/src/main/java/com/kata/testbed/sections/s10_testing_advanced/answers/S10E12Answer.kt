package com.kata.testbed.sections.s10_testing_advanced.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private data class Quote(val text: String, val author: String)

private sealed interface LoadState<out T> {
    data object Loading : LoadState<Nothing>
    data class Loaded<T>(val data: T) : LoadState<T>
}

private suspend fun fetchQuote(index: Int): Quote {
    delay(1500) // Simulate network delay
    val quotes = listOf(
        Quote("Simplicity is the ultimate sophistication.", "Leonardo da Vinci"),
        Quote("Code is read more often than it is written.", "Guido van Rossum"),
        Quote("Make it work, make it right, make it fast.", "Kent Beck")
    )
    return quotes[index % quotes.size]
}

@Composable
fun S10E12Answer() {
    var quoteIndex by remember { mutableIntStateOf(0) }

    val quoteState by produceState<LoadState<Quote>>(
        initialValue = LoadState.Loading,
        key1 = quoteIndex
    ) {
        value = LoadState.Loading
        val quote = fetchQuote(quoteIndex)
        value = LoadState.Loaded(quote)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("produceState", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Converts a suspend function into Compose State with automatic lifecycle management.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        when (val state = quoteState) {
            is LoadState.Loading -> {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Loading quote...")
            }
            is LoadState.Loaded -> {
                Text(
                    "\"${state.data.text}\"",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text("- ${state.data.author}", color = Color(0xFF666666))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { quoteIndex++ }) {
            Text("Load Next Quote")
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: produceState launches a coroutine scoped to the composable lifecycle. " +
                "Setting value triggers recomposition. key1 restarts when the key changes.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
