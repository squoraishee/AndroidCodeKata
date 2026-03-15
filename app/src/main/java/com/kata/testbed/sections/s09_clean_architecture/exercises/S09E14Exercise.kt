package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E14Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Dependency Rule")
        // TODO: Create domain classes with zero Android imports
        // TODO: Create a "dependency checker" that lists imports for each layer
        // TODO: Verify domain layer has no Android/framework imports
        // TODO: Display pass/fail results for each layer
    }
}
