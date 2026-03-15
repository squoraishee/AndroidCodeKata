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

data class E06User(val id: String, val name: String, val email: String)

sealed class E06Result<out T> {
    data class Success<T>(val data: T) : E06Result<T>()
    data class Error(val message: String) : E06Result<Nothing>()
}

interface E06UserRepository {
    fun getById(id: String): E06User?
}

class E06InMemoryUserRepository(private val users: List<E06User>) : E06UserRepository {
    override fun getById(id: String): E06User? = users.find { it.id == id }
}

class E06GetUserUseCase(private val repository: E06UserRepository) {
    operator fun invoke(id: String): E06Result<E06User> {
        val user = repository.getById(id)
        return if (user != null) {
            E06Result.Success(user)
        } else {
            E06Result.Error("User with id '$id' not found")
        }
    }
}

@Composable
fun S09E06Answer() {
    val repo = E06InMemoryUserRepository(listOf(E06User("1", "Alice", "alice@example.com")))
    val getUser = E06GetUserUseCase(repo)

    val successResult = getUser("1")
    val errorResult = getUser("99")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Result Wrapper", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Sealed class Result<T> wraps success and error outcomes.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("Success case:", fontWeight = FontWeight.Bold)
        when (successResult) {
            is E06Result.Success -> Text("  ${successResult.data.name}", color = Color(0xFF2E7D32))
            is E06Result.Error -> Text("  ${successResult.message}", color = Color(0xFFC62828))
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("Error case:", fontWeight = FontWeight.Bold)
        when (errorResult) {
            is E06Result.Success -> Text("  ${errorResult.data.name}", color = Color(0xFF2E7D32))
            is E06Result.Error -> Text("  ${errorResult.message}", color = Color(0xFFC62828))
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Result<T> forces callers to handle both success and error. " +
                "Exhaustive when ensures no case is missed.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
