package com.kata.testbed.sections.s09_clean_architecture.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class E09User(val id: String, val name: String)

sealed class E09DomainError {
    data class NotFound(val id: String) : E09DomainError()
    data object Unauthorized : E09DomainError()
    data class NetworkError(val reason: String) : E09DomainError()
}

sealed class DomainResult<out T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Failure(val error: E09DomainError) : DomainResult<Nothing>()
}

sealed interface E09UiState {
    data object Loading : E09UiState
    data class Loaded(val userName: String) : E09UiState
    data class ErrorState(val message: String, val isRetryable: Boolean) : E09UiState
}

fun E09DomainError.toUiState(): E09UiState = when (this) {
    is E09DomainError.NotFound -> E09UiState.ErrorState("User '$id' not found", isRetryable = false)
    is E09DomainError.Unauthorized -> E09UiState.ErrorState("Please log in to continue", isRetryable = false)
    is E09DomainError.NetworkError -> E09UiState.ErrorState("Network: $reason", isRetryable = true)
}

class E09GetUserUseCase {
    fun invoke(id: String, simulateError: E09DomainError? = null): DomainResult<E09User> {
        if (simulateError != null) return DomainResult.Failure(simulateError)
        return DomainResult.Success(E09User(id, "Alice"))
    }
}

@Composable
fun S09E09Answer() {
    val useCase = E09GetUserUseCase()

    val scenarios = listOf(
        "Success" to useCase.invoke("1"),
        "Not Found" to useCase.invoke("99", E09DomainError.NotFound("99")),
        "Unauthorized" to useCase.invoke("1", E09DomainError.Unauthorized),
        "Network Error" to useCase.invoke("1", E09DomainError.NetworkError("timeout"))
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Error Propagation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Domain errors flow through use case to UI state with typed handling.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        scenarios.forEach { (label, result) ->
            Text("$label:", fontWeight = FontWeight.Bold)
            val uiState = when (result) {
                is DomainResult.Success -> E09UiState.Loaded(result.data.name)
                is DomainResult.Failure -> result.error.toUiState()
            }
            when (uiState) {
                is E09UiState.Loading -> Text("  Loading...")
                is E09UiState.Loaded -> Text("  User: ${uiState.userName}", color = Color(0xFF2E7D32))
                is E09UiState.ErrorState -> {
                    Text("  ${uiState.message}", color = Color(0xFFC62828))
                    Text("  Retryable: ${uiState.isRetryable}")
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Errors are domain types, not exceptions. Each layer maps them to its own representation.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
