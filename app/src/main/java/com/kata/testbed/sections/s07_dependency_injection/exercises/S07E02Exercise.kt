package com.kata.testbed.sections.s07_dependency_injection.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S07E02Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Define a Logger interface with fun log(message: String): String
        // TODO: Create ConsoleLogger that returns "Console: $message"
        // TODO: Create FileLogger that returns "File: $message"
        // TODO: Create a NotificationService that takes a Logger via constructor
        // TODO: Show polymorphic behavior by injecting both loggers
    }
}
