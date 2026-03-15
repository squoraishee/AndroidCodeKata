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
fun S01E05Answer() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // filter keeps only elements matching the predicate
    val evens = numbers.filter { it % 2 == 0 }

    // map transforms each element
    val squares = numbers.map { it * it }

    // Chaining: filter then map
    val evenSquares = numbers.filter { it % 2 == 0 }.map { it * it }

    val people = mapOf("Alice" to 30, "Bob" to 25, "Carol" to 35)

    // Maps can be filtered too — filter gives Map.Entry with key/value
    val over28 = people.filter { (_, age) -> age > 28 }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Collections", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Original: $numbers", style = MaterialTheme.typography.bodyLarge)
        Text("Evens: $evens", style = MaterialTheme.typography.bodyLarge)
        Text("Squares: $squares", style = MaterialTheme.typography.bodyLarge)
        Text("Even squares: $evenSquares", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("People: $people", style = MaterialTheme.typography.bodyLarge)
        Text("Over 28: $over28", style = MaterialTheme.typography.bodyLarge)
    }
}
