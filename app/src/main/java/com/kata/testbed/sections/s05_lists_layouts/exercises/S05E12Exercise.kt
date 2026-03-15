package com.kata.testbed.sections.s05_lists_layouts.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S05E12Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a scroll state variable for tracking scroll offset
        // TODO: Create a collapsible header section with animated height
        // TODO: The header should shrink as the user scrolls down
        // TODO: Use a LazyColumn with Modifier.height(350.dp) for the list content
        // TODO: Display 30 items in the list
        // TODO: The header height should animate between expanded and collapsed
        // HINT: Use rememberLazyListState and derive the collapse fraction
    }
}
