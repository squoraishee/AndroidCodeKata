package com.kata.testbed.sections.s01_kotlin_basics.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S01E09Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Write a function `applyToList(list: List<Int>, transform: (Int) -> Int): List<Int>`
        //       that applies the transform lambda to each element
        // TODO: Create a list of numbers: listOf(1, 2, 3, 4, 5)
        // TODO: Call applyToList with a doubling lambda { it * 2 }, display result
        // TODO: Call applyToList with a squaring lambda { it * it }, display result
        // TODO: Demonstrate passing a function reference, display result
    }
}
