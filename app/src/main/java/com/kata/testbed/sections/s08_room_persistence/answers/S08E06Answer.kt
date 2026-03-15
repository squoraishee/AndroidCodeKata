package com.kata.testbed.sections.s08_room_persistence.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class Contact(val id: Int, val name: String, val email: String, val city: String)

private class FakeContactDao {
    private val contacts = listOf(
        Contact(1, "Alice Smith", "alice@example.com", "New York"),
        Contact(2, "Bob Johnson", "bob@example.com", "San Francisco"),
        Contact(3, "Charlie Brown", "charlie@example.com", "New York"),
        Contact(4, "Diana Prince", "diana@example.com", "Chicago"),
        Contact(5, "Alice Wonder", "awonder@example.com", "Boston")
    )

    fun getByCity(city: String): List<Contact> =
        contacts.filter { it.city == city }

    fun searchByName(query: String): List<Contact> =
        contacts.filter { it.name.contains(query, ignoreCase = true) }

    fun getById(id: Int): Contact? =
        contacts.find { it.id == id }
}

@Composable
fun S08E06Answer() {
    val dao = FakeContactDao()
    val nyContacts = dao.getByCity("New York")
    val aliceResults = dao.searchByName("Alice")
    val single = dao.getById(4)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Parameterized Queries",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "DAO query patterns:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Query(\"SELECT * FROM contacts WHERE city = :city\")\nsuspend fun getByCity(city: String): List<Contact>\n\n@Query(\"SELECT * FROM contacts WHERE name LIKE '%' || :q || '%'\")\nsuspend fun searchByName(q: String): List<Contact>\n\n@Query(\"SELECT * FROM contacts WHERE id = :id\")\nsuspend fun getById(id: Int): Contact?",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "WHERE city = 'New York':", fontWeight = FontWeight.SemiBold)
        nyContacts.forEach { Text(text = "  ${it.name} (${it.email})") }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "LIKE '%Alice%':", fontWeight = FontWeight.SemiBold)
        aliceResults.forEach { Text(text = "  ${it.name} (${it.city})") }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "WHERE id = 4:", fontWeight = FontWeight.SemiBold)
        Text(text = "  ${single?.name ?: "Not found"}")
    }
}
