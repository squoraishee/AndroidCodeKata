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

private data class ServerConfig(
    var host: String = "",
    var port: Int = 0,
    var secure: Boolean = false
)

@Composable
fun S01E10Answer() {
    // apply — configures an object, returns the object itself
    // Use when: initializing/configuring an object
    val config = ServerConfig().apply {
        host = "api.example.com"
        port = 443
        secure = true
    }

    // let — transforms a value, returns the lambda result
    // Use when: null-safe transformations, scoping a value
    val nullable: String? = "Hello"
    val letResult = nullable?.let { "$it World (length: ${it.length})" } ?: "was null"

    // run — like let but uses `this` instead of `it`
    // Use when: computing a value from an object's properties
    val runResult = config.run { "$host:$port (secure=$secure)" }

    // also — performs a side effect, returns the original object
    // Use when: logging, debugging, additional actions
    val log = mutableListOf<String>()
    val alsoResult = config.also { log.add("Created config: ${it.host}") }

    // with — like run but takes the object as a parameter
    // Use when: calling multiple methods on the same object
    val withResult = with(config) { "Connecting to $host on port $port" }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Scope Functions", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("apply: $config", style = MaterialTheme.typography.bodyLarge)
        Text("let: $letResult", style = MaterialTheme.typography.bodyLarge)
        Text("run: $runResult", style = MaterialTheme.typography.bodyLarge)
        Text("also log: ${log.first()}", style = MaterialTheme.typography.bodyLarge)
        Text("with: $withResult", style = MaterialTheme.typography.bodyLarge)
    }
}
