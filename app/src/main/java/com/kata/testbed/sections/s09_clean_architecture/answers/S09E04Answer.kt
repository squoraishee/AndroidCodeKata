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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class E04User(val id: String, val name: String, val email: String)

interface E04UserRepository {
    fun getAll(): List<E04User>
}

class E04InMemoryUserRepository(private val users: List<E04User>) : E04UserRepository {
    override fun getAll(): List<E04User> = users
}

class E04GetUsersUseCase(private val repository: E04UserRepository) {
    operator fun invoke(): List<E04User> = repository.getAll()
}

@Composable
fun S09E04Answer() {
    val repo = E04InMemoryUserRepository(
        listOf(
            E04User("1", "Alice", "alice@example.com"),
            E04User("2", "Bob", "bob@example.com"),
            E04User("3", "Charlie", "charlie@example.com")
        )
    )
    val getUsers = E04GetUsersUseCase(repo)

    val users = getUsers()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Simple Use Case", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Use case wraps a single repository operation with operator fun invoke().")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("getUsers() result:", fontWeight = FontWeight.Bold)
        users.forEach { user ->
            Text("  ${user.name} - ${user.email}")
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: operator fun invoke() lets you call the use case like a function: getUsers(). " +
                "The ViewModel depends on the use case, not the repository directly.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
