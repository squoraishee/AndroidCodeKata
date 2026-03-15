package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E04Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Bottom Navigation")
        // TODO: Define an enum for tabs: Home, Search, Profile
        // TODO: Track selected tab with state
        // TODO: Create a NavigationBar with NavigationBarItem for each tab
        // TODO: Show different content based on the selected tab
    }
}
