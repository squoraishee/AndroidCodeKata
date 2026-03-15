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

@Composable
fun S01E03Answer() {
    val language = "Kotlin"
    val version = 2.0

    // Simple variable reference with $
    val simple = "I love $language"

    // Expression inside ${} — any valid Kotlin expression works
    val withExpression = "$language has ${language.length} characters"

    // Conditional expression inside template
    val conditional = "$language $version is ${if (version >= 2.0) "modern" else "classic"}"

    // Raw string with trimMargin strips leading whitespace up to the | character
    val rawString = """
        |Language: $language
        |Version: $version
        |Status: Active
    """.trimMargin()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("String Templates", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(simple, style = MaterialTheme.typography.bodyLarge)
        Text(withExpression, style = MaterialTheme.typography.bodyLarge)
        Text(conditional, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Raw string:", style = MaterialTheme.typography.bodyMedium)
        Text(rawString, style = MaterialTheme.typography.bodyLarge)
    }
}
