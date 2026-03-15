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
fun S01E04Answer() {
    val score = 85

    // when replaces switch statements — it matches the first true branch
    val grade = when (score) {
        100 -> "Perfect"
        in 90..99 -> "Excellent"
        in 80..89 -> "Good"
        in 70..79 -> "Average"
        else -> "Needs Improvement"
    }

    // when can also match on type using `is`
    fun describeValue(value: Any): String = when (value) {
        is String -> "String of length ${value.length}"
        is Int -> "Integer: $value"
        is Boolean -> "Boolean: $value"
        is List<*> -> "List with ${value.size} items"
        else -> "Unknown type"
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("When Expressions", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Score: $score -> Grade: $grade", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Type matching:", style = MaterialTheme.typography.bodyMedium)
        Text(describeValue("Hello"), style = MaterialTheme.typography.bodyLarge)
        Text(describeValue(42), style = MaterialTheme.typography.bodyLarge)
        Text(describeValue(true), style = MaterialTheme.typography.bodyLarge)
        Text(describeValue(listOf(1, 2, 3)), style = MaterialTheme.typography.bodyLarge)
    }
}
