package com.kata.testbed.sections.s05_lists_layouts.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S05E15Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a list that has performance issues to demonstrate fixes
        // TODO: Problem 1: Missing keys — add key parameter to items()
        // TODO: Problem 2: Unnecessary recomposition — use remember for computed values
        // TODO: Problem 3: Unstable lambda — use remember for click handlers
        // TODO: Add a recomposition counter using SideEffect to track recompositions
        // TODO: Show a "Toggle Fix" button that switches between bad and good implementations
        // TODO: Display recomposition count to show the improvement
    }
}
