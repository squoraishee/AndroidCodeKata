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

// `inline` copies the function body into call sites, avoiding lambda object allocation.
// `reified` preserves the actual type T at runtime (normally erased by JVM).
// Without `inline`, you cannot use `reified`.
private inline fun <reified T> filterByType(list: List<Any>): List<T> {
    return list.filterIsInstance<T>()
}

// reified lets you access T::class at runtime
private inline fun <reified T> typeName(): String {
    return T::class.simpleName ?: "Unknown"
}

@Composable
fun S01E14Answer() {
    val mixed: List<Any> = listOf(1, "hello", 2.5, "world", 42, true, "Kotlin")

    // reified T means the actual type is known at runtime
    val strings = filterByType<String>(mixed)
    val ints = filterByType<Int>(mixed)
    val doubles = filterByType<Double>(mixed)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Inline and Reified", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Mixed list: $mixed", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("filterByType<String>: $strings", style = MaterialTheme.typography.bodyLarge)
        Text("filterByType<Int>: $ints", style = MaterialTheme.typography.bodyLarge)
        Text("filterByType<Double>: $doubles", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("typeName<String>() = ${typeName<String>()}", style = MaterialTheme.typography.bodyLarge)
        Text("typeName<Int>() = ${typeName<Int>()}", style = MaterialTheme.typography.bodyLarge)
        Text("typeName<List<*>>() = ${typeName<List<*>>()}", style = MaterialTheme.typography.bodyLarge)
    }
}
