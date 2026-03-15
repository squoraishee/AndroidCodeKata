package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E15Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Multi-Module Navigation")
        // TODO: Define a NavigationEntry interface with route: String and content: @Composable () -> Unit
        // TODO: Create simulated "modules" that register their NavigationEntry implementations
        // TODO: Build a registry that collects all entries and renders the correct one
        // TODO: Show how modules don't depend on each other, only on the shared contract
    }
}
