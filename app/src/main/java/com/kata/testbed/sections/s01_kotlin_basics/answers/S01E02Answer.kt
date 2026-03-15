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
fun S01E02Answer() {
    // The ? suffix marks a type as nullable
    val name: String? = "Kotlin"
    val nullName: String? = null

    // Safe call (?.) returns null instead of throwing NPE
    val nameLength = name?.length

    // Elvis (?:) provides a default when the left side is null
    val displayName = nullName ?: "Unknown"

    // let runs a block only when the value is non-null
    val letResult = name?.let { "Name is $it" } ?: "Name was null"

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Null Safety", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Safe call - name?.length: $nameLength", style = MaterialTheme.typography.bodyLarge)
        Text("Elvis - nullName ?: default: $displayName", style = MaterialTheme.typography.bodyLarge)
        Text("let block: $letResult", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        // Chaining safe calls
        val upperLength = nullName?.uppercase()?.length
        Text("Chained safe call on null: $upperLength", style = MaterialTheme.typography.bodyLarge)

        val validLength = name?.uppercase()?.length
        Text("Chained safe call on value: $validLength", style = MaterialTheme.typography.bodyLarge)
    }
}
