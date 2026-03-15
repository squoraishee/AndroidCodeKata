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

// Generic class — T is the type parameter, decided at usage site
private class Stack<T> {
    private val elements = mutableListOf<T>()

    val size: Int get() = elements.size

    fun push(item: T) { elements.add(item) }

    fun pop(): T? = if (elements.isNotEmpty()) elements.removeAt(elements.lastIndex) else null

    fun peek(): T? = elements.lastOrNull()

    fun isEmpty(): Boolean = elements.isEmpty()

    override fun toString(): String = elements.toString()
}

@Composable
fun S01E11Answer() {
    // Stack<Int> — the generic type is Int
    val intStack = Stack<Int>()
    intStack.push(1)
    intStack.push(2)
    intStack.push(3)
    val peeked = intStack.peek()
    val popped = intStack.pop()

    // Stack<String> — same class, different type
    val stringStack = Stack<String>()
    stringStack.push("Alice")
    stringStack.push("Bob")
    stringStack.push("Carol")
    val poppedName = stringStack.pop()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Generics", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Int Stack after push 1,2,3:", style = MaterialTheme.typography.bodyMedium)
        Text("  peek(): $peeked", style = MaterialTheme.typography.bodyLarge)
        Text("  pop(): $popped", style = MaterialTheme.typography.bodyLarge)
        Text("  remaining: $intStack (size=${intStack.size})", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("String Stack after push Alice,Bob,Carol:", style = MaterialTheme.typography.bodyMedium)
        Text("  pop(): $poppedName", style = MaterialTheme.typography.bodyLarge)
        Text("  remaining: $stringStack", style = MaterialTheme.typography.bodyLarge)
    }
}
