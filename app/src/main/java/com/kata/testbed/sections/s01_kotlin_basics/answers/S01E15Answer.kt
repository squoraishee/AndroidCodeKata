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

// DSL building blocks: a data model and a builder with a lambda-with-receiver

private data class MenuItem(val name: String, val price: Double)

private class MenuBuilder {
    private val items = mutableListOf<MenuItem>()

    // Each call to `item` adds a MenuItem to the builder's list
    fun item(name: String, price: Double) {
        items.add(MenuItem(name, price))
    }

    fun build(): List<MenuItem> = items.toList()
}

// The top-level DSL entry point: `menu { ... }`
// The lambda receiver (MenuBuilder.() -> Unit) means `this` inside the block is a MenuBuilder
private fun menu(block: MenuBuilder.() -> Unit): List<MenuItem> {
    return MenuBuilder().apply(block).build()
}

@Composable
fun S01E15Answer() {
    // The DSL reads almost like a specification, not code
    val cafeMenu = menu {
        item("Espresso", 3.50)
        item("Cappuccino", 4.50)
        item("Green Tea", 2.50)
        item("Croissant", 3.00)
        item("Chocolate Cake", 5.50)
    }

    val total = cafeMenu.sumOf { it.price }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("DSL Building", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Cafe Menu:", style = MaterialTheme.typography.bodyMedium)
        cafeMenu.forEach { item ->
            Text(
                "  ${item.name} — $${String.format("%.2f", item.price)}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Total: $${String.format("%.2f", total)}", style = MaterialTheme.typography.bodyLarge)
    }
}
