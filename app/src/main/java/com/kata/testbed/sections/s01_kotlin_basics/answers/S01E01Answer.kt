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
fun S01E01Answer() {
    // `val` is immutable — once assigned, it cannot change
    val name = "Kotlin"

    // `var` is mutable — it can be reassigned
    var count = 0
    count += 1

    Column(modifier = Modifier.padding(16.dp)) {
        Text("val vs var", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Language: $name", style = MaterialTheme.typography.bodyLarge)
        Text("Count after increment: $count", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        // Type inference: Kotlin figures out the types automatically
        val pi = 3.14          // Double
        val isKotlin = true    // Boolean
        val letter = 'K'       // Char
        Text("Pi: $pi (Double)", style = MaterialTheme.typography.bodyLarge)
        Text("Is Kotlin: $isKotlin (Boolean)", style = MaterialTheme.typography.bodyLarge)
        Text("Letter: $letter (Char)", style = MaterialTheme.typography.bodyLarge)
    }
}
