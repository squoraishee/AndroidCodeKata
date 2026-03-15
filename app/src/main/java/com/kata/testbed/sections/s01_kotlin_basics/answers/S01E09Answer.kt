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

// A higher-order function takes a function as a parameter or returns one.
// The `transform` parameter is a lambda: (Int) -> Int
private fun applyToList(list: List<Int>, transform: (Int) -> Int): List<Int> {
    return list.map(transform)
}

// A function that returns a lambda
private fun multiplier(factor: Int): (Int) -> Int {
    return { it * factor }
}

@Composable
fun S01E09Answer() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // Passing lambdas directly
    val doubled = applyToList(numbers) { it * 2 }
    val squared = applyToList(numbers) { it * it }

    // Using a function reference (::) instead of a lambda
    fun negate(n: Int): Int = -n
    val negated = applyToList(numbers, ::negate)

    // Using a function that returns a lambda
    val tripled = applyToList(numbers, multiplier(3))

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Higher-Order Functions", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Original: $numbers", style = MaterialTheme.typography.bodyLarge)
        Text("Doubled: $doubled", style = MaterialTheme.typography.bodyLarge)
        Text("Squared: $squared", style = MaterialTheme.typography.bodyLarge)
        Text("Negated (fn ref): $negated", style = MaterialTheme.typography.bodyLarge)
        Text("Tripled (returned fn): $tripled", style = MaterialTheme.typography.bodyLarge)
    }
}
