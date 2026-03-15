package com.kata.testbed.sections.s01_kotlin_basics.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Sealed classes restrict which subclasses can exist — the compiler
// knows all cases, so `when` can be exhaustive without `else`
private sealed class NetworkResult {
    data class Success(val data: String) : NetworkResult()
    data class Error(val message: String) : NetworkResult()
    data object Loading : NetworkResult()
}

@Composable
fun S01E07Answer() {
    val results = listOf(
        NetworkResult.Success("User data loaded"),
        NetworkResult.Error("Network timeout"),
        NetworkResult.Loading
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Sealed Classes", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        results.forEach { result ->
            // Exhaustive when — the compiler ensures every case is handled
            val (label, color) = when (result) {
                is NetworkResult.Success -> "Success: ${result.data}" to Color(0xFF4CAF50)
                is NetworkResult.Error -> "Error: ${result.message}" to Color(0xFFF44336)
                is NetworkResult.Loading -> "Loading..." to Color(0xFFFF9800)
            }
            Text(label, color = color, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
