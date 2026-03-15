package com.kata.testbed.sections.s01_kotlin_basics.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S01E02Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Declare a nullable String `name: String?` = "Kotlin"
        // TODO: Declare another nullable String `nullName: String?` = null
        // TODO: Use safe call (?.) to get the length of `name`, display it
        // TODO: Use Elvis operator (?:) on `nullName` to provide a default, display it
        // TODO: Use `name?.let { }` to display the value only if non-null
    }
}
