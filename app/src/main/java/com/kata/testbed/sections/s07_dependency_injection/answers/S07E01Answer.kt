package com.kata.testbed.sections.s07_dependency_injection.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private interface UserRepository {
    fun getUser(id: Int): String
}

private class InMemoryUserRepository : UserRepository {
    private val users = mapOf(1 to "Alice", 2 to "Bob", 3 to "Charlie")
    override fun getUser(id: Int): String = users[id] ?: "Unknown"
}

private class UserService(private val repository: UserRepository) {
    fun getUserGreeting(id: Int): String {
        val name = repository.getUser(id)
        return "Hello, $name!"
    }
}

@Composable
fun S07E01Answer() {
    val repository: UserRepository = InMemoryUserRepository()
    val service = UserService(repository)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Constructor Injection",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "UserService receives UserRepository via constructor:")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "class UserService(private val repository: UserRepository)",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = service.getUserGreeting(1))
        Text(text = service.getUserGreeting(2))
        Text(text = service.getUserGreeting(3))
        Text(text = service.getUserGreeting(99))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Dependencies are explicit and visible in the constructor signature.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
