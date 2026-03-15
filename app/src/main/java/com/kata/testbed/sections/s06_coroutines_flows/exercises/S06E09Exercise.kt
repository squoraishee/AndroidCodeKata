package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E09Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a MutableSharedFlow<String>() (no replay)
        // TODO: Create a mutableStateListOf to hold received events
        // TODO: Use LaunchedEffect to collect from the SharedFlow
        //   Add each event to the received list
        // TODO: Create buttons that emit different events:
        //   "Info: ...", "Warning: ...", "Error: ..."
        // TODO: Use rememberCoroutineScope to emit events from button clicks
        // TODO: Display the received events list
    }
}
