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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class E02User(val id: String, val name: String, val email: String)

interface E02UserRepository {
    fun getAll(): List<E02User>
    fun getById(id: String): E02User?
    fun save(user: E02User)
    fun delete(id: String)
}

@Composable
fun S09E02Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Repository Interface", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("The interface lives in the domain layer. Data layer provides the implementation.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("Interface Contract:", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))

        val code = """
interface UserRepository {
    fun getAll(): List<User>
    fun getById(id: String): User?
    fun save(user: User)
    fun delete(id: String)
}
        """.trimIndent()
        Text(code, fontFamily = FontFamily.Monospace, fontSize = 12.sp)

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Methods defined:", fontWeight = FontWeight.Bold)
        Text("  getAll() -> List<User>")
        Text("  getById(id) -> User?")
        Text("  save(user) -> Unit")
        Text("  delete(id) -> Unit")

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Domain defines WHAT, data layer defines HOW. " +
                "This enables swapping implementations (Room, network, in-memory).",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
