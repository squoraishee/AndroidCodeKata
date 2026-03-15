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

data class E01User(
    val id: String,
    val name: String,
    val email: String
)

@Composable
fun S09E01Answer() {
    val user = E01User(id = "1", name = "Alice", email = "alice@example.com")
    val user2 = user.copy(id = "2", name = "Bob", email = "bob@example.com")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Domain Model", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("A domain model is a plain Kotlin data class with zero framework dependencies.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("User 1:", fontWeight = FontWeight.Bold)
        Text("  id: ${user.id}")
        Text("  name: ${user.name}")
        Text("  email: ${user.email}")

        Spacer(modifier = Modifier.height(8.dp))
        Text("User 2 (copied with changes):", fontWeight = FontWeight.Bold)
        Text("  id: ${user2.id}")
        Text("  name: ${user2.name}")
        Text("  email: ${user2.email}")

        Spacer(modifier = Modifier.height(8.dp))
        Text("equals: ${user == user2}")
        Text("toString: $user")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: No Android imports, no annotations, no framework coupling. " +
                "This class can be tested with plain JUnit.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
