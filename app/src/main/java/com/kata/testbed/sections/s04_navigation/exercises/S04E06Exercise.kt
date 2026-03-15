package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E06Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Deep Links")
        // TODO: Create a text field where the user enters a simulated deep link path
        // TODO: Parse the path and navigate to the matching screen
        // TODO: e.g., "/item/42" goes to detail screen with id=42
        // TODO: Show an error for unrecognized paths
    }
}
