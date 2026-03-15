package com.kata.testbed.sections.s01_kotlin_basics.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S01E15Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a `Menu` class with a list of `MenuItem(val name: String, val price: Double)`
        // TODO: Create a `MenuBuilder` class with functions: item(name, price), category(name, block)
        // TODO: Write a top-level `menu(block: MenuBuilder.() -> Unit): Menu` function
        // TODO: Use the DSL to build a menu:
        //   val myMenu = menu {
        //       item("Coffee", 3.50)
        //       item("Tea", 2.50)
        //       item("Cake", 5.00)
        //   }
        // TODO: Display each menu item using Text composables
    }
}
