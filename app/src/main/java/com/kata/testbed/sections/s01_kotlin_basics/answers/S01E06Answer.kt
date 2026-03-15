package com.kata.testbed.sections.s01_kotlin_basics.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// data class auto-generates: toString, equals, hashCode, copy, componentN
data class User(val name: String, val age: Int, val email: String)

@Composable
fun S01E06Answer() {
    val alice = User("Alice", 30, "alice@example.com")

    // copy() creates a new instance, changing only specified fields
    val olderAlice = alice.copy(age = 31)

    // Destructuring extracts components by position
    val (name, age, email) = alice

    // equals() compares by value, not by reference
    val sameAlice = User("Alice", 30, "alice@example.com")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Data Classes", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("toString: $alice", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("copy(age=31): $olderAlice", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Destructured — name: $name, age: $age", style = MaterialTheme.typography.bodyLarge)
        Text("  email: $email", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("alice == sameAlice: ${alice == sameAlice}", style = MaterialTheme.typography.bodyLarge)
        Text("alice === sameAlice: ${alice === sameAlice}", style = MaterialTheme.typography.bodyLarge)
    }
}
