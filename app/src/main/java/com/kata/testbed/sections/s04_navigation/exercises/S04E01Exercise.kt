package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E01Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Basic NavHost")
        // TODO: Create a sealed interface Screen with Home and Detail variants
        // TODO: Hold current screen in state
        // TODO: Show Home content with a "Go to Detail" button
        // TODO: Show Detail content with a "Go Back" button
    }
}
