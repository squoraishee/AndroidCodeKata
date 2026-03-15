package com.kata.testbed.sections.s01_kotlin_basics.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S01E14Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a mixed list: listOf(1, "hello", 2.5, "world", 42, true, "Kotlin")
        // TODO: Write an inline function `filterByType<reified T>(list: List<Any>): List<T>`
        //       that filters the list to only items of type T
        // TODO: Call filterByType<String>(...) and display the result
        // TODO: Call filterByType<Int>(...) and display the result
        // TODO: Write an inline function that returns the type name using T::class.simpleName
    }
}
