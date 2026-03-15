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

// `object` declares a singleton — exactly one instance, created lazily
private object Logger {
    private val logs = mutableListOf<String>()

    fun log(message: String) { logs.add(message) }

    fun getAll(): List<String> = logs.toList()

    fun clear() { logs.clear() }
}

// `companion object` lives inside a class — provides factory methods and constants
private class DatabaseConnection private constructor(val url: String) {
    companion object {
        const val MAX_CONNECTIONS = 10

        fun create(url: String): DatabaseConnection {
            Logger.log("Creating connection to $url")
            return DatabaseConnection(url)
        }
    }

    override fun toString() = "DB($url)"
}

@Composable
fun S01E12Answer() {
    // Reset logger state for each recomposition
    Logger.clear()

    Logger.log("App started")
    Logger.log("Initializing database")

    val db = DatabaseConnection.create("jdbc:postgresql://localhost/mydb")

    Logger.log("Connection established")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Object and Companion", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Singleton Logger entries:", style = MaterialTheme.typography.bodyMedium)
        Logger.getAll().forEach { entry ->
            Text("  - $entry", style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Companion factory: $db", style = MaterialTheme.typography.bodyLarge)
        Text("MAX_CONNECTIONS: ${DatabaseConnection.MAX_CONNECTIONS}", style = MaterialTheme.typography.bodyLarge)
    }
}
