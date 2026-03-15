package com.kata.testbed.sections.s05_lists_layouts.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S05E06Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a mutableStateOf for isRefreshing (Boolean)
        // TODO: Create a mutableStateListOf for items with timestamps
        // TODO: Add a "Refresh" button at the top
        // TODO: When clicked, set isRefreshing = true, simulate delay with LaunchedEffect
        // TODO: After delay, update items with new timestamps, set isRefreshing = false
        // TODO: Show CircularProgressIndicator when isRefreshing
        // TODO: Display items in a LazyColumn with Modifier.height(300.dp)
    }
}
