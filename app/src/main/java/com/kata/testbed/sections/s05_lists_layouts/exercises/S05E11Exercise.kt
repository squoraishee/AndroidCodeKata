package com.kata.testbed.sections.s05_lists_layouts.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S05E11Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a rememberLazyListState()
        // TODO: Display the first visible item index using Text
        // TODO: Create a LazyColumn with Modifier.height(350.dp) using that state
        // TODO: Display 50 items
        // TODO: Derive whether to show scroll-to-top button using derivedStateOf
        // TODO: Show a "Scroll to Top" button when firstVisibleItemIndex > 0
        // TODO: Use rememberCoroutineScope() to call listState.animateScrollToItem(0)
    }
}
