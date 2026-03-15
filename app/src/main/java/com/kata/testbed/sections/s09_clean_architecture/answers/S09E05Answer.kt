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

data class E05User(val id: String, val name: String, val email: String)

interface E05UserRepository {
    fun getById(id: String): E05User?
}

class E05InMemoryUserRepository(private val users: List<E05User>) : E05UserRepository {
    override fun getById(id: String): E05User? = users.find { it.id == id }
}

class E05GetUserByIdUseCase(private val repository: E05UserRepository) {
    operator fun invoke(id: String): E05User? = repository.getById(id)
}

@Composable
fun S09E05Answer() {
    val repo = E05InMemoryUserRepository(
        listOf(
            E05User("1", "Alice", "alice@example.com"),
            E05User("2", "Bob", "bob@example.com")
        )
    )
    val getUserById = E05GetUserByIdUseCase(repo)

    val found = getUserById("1")
    val notFound = getUserById("99")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Parameterized Use Case", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Use case accepts input parameters and returns a typed output.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("getUserById(\"1\"):", fontWeight = FontWeight.Bold)
        if (found != null) {
            Text("  Found: ${found.name} (${found.email})", color = Color(0xFF2E7D32))
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("getUserById(\"99\"):", fontWeight = FontWeight.Bold)
        if (notFound == null) {
            Text("  Not found (null)", color = Color(0xFFC62828))
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: The use case signature operator fun invoke(id: String): User? clearly " +
                "communicates its input/output contract.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
