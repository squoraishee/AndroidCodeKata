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

data class E03User(val id: String, val name: String, val email: String)

interface E03UserRepository {
    fun getAll(): List<E03User>
    fun getById(id: String): E03User?
    fun save(user: E03User)
    fun delete(id: String)
}

class E03InMemoryUserRepository : E03UserRepository {
    private val users = mutableListOf<E03User>()

    override fun getAll(): List<E03User> = users.toList()

    override fun getById(id: String): E03User? = users.find { it.id == id }

    override fun save(user: E03User) {
        val index = users.indexOfFirst { it.id == user.id }
        if (index >= 0) users[index] = user else users.add(user)
    }

    override fun delete(id: String) {
        users.removeAll { it.id == id }
    }
}

@Composable
fun S09E03Answer() {
    val repo: E03UserRepository = E03InMemoryUserRepository()

    repo.save(E03User("1", "Alice", "alice@example.com"))
    repo.save(E03User("2", "Bob", "bob@example.com"))
    repo.save(E03User("3", "Charlie", "charlie@example.com"))

    val allBefore = repo.getAll()
    val found = repo.getById("2")
    repo.delete("2")
    val allAfter = repo.getAll()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Repository Implementation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Text("After saving 3 users:", fontWeight = FontWeight.Bold)
        allBefore.forEach { Text("  ${it.name} (${it.email})") }

        Spacer(modifier = Modifier.height(8.dp))
        Text("getById(\"2\"): ${found?.name ?: "null"}", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))
        Text("After deleting id \"2\":", fontWeight = FontWeight.Bold)
        allAfter.forEach { Text("  ${it.name} (${it.email})") }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: InMemoryUserRepository implements the domain interface. " +
                "Could be replaced with RoomUserRepository without changing domain code.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
