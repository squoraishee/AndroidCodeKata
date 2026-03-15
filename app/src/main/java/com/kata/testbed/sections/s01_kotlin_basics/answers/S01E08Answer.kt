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

// Extension functions add methods to existing classes without modifying them.
// `this` inside the function refers to the receiver object.
private fun String.addExcitement(): String = "$this!!!"

private fun Int.isEven(): Boolean = this % 2 == 0

private fun String.wordCount(): Int = this.trim().split("\\s+".toRegex()).size

// Extension properties work the same way
private val String.reversed: String get() = this.reversed()

@Composable
fun S01E08Answer() {
    val greeting = "Hello Kotlin"
    val number = 42

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Extension Functions", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("\"$greeting\".addExcitement() = ${greeting.addExcitement()}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("$number.isEven() = ${number.isEven()}", style = MaterialTheme.typography.bodyLarge)
        Text("${number + 1}.isEven() = ${(number + 1).isEven()}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("\"$greeting\".wordCount() = ${greeting.wordCount()}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("\"$greeting\".reversed = ${greeting.reversed}", style = MaterialTheme.typography.bodyLarge)
    }
}
