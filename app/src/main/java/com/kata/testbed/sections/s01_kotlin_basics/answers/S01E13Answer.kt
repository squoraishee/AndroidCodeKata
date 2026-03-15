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
import kotlin.properties.Delegates

@Composable
fun S01E13Answer() {
    // observable fires a callback whenever the property changes
    val changeLog = mutableListOf<String>()

    var observedName: String by Delegates.observable("initial") { _, old, new ->
        changeLog.add("'$old' -> '$new'")
    }

    observedName = "Alice"
    observedName = "Bob"
    observedName = "Carol"

    // vetoable can reject changes — the callback returns true to accept, false to reject
    val vetoLog = mutableListOf<String>()
    var positiveNumber: Int by Delegates.vetoable(0) { _, _, new ->
        val accepted = new >= 0
        vetoLog.add("Set to $new: ${if (accepted) "accepted" else "rejected"}")
        accepted
    }

    positiveNumber = 10
    positiveNumber = -5   // This will be rejected
    positiveNumber = 20

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Delegation", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Observable property changes:", style = MaterialTheme.typography.bodyMedium)
        changeLog.forEach { entry ->
            Text("  $entry", style = MaterialTheme.typography.bodyLarge)
        }
        Text("Final value: $observedName", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Vetoable property (rejects negatives):", style = MaterialTheme.typography.bodyMedium)
        vetoLog.forEach { entry ->
            Text("  $entry", style = MaterialTheme.typography.bodyLarge)
        }
        Text("Final value: $positiveNumber", style = MaterialTheme.typography.bodyLarge)
    }
}
