package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E06Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a flow using the flow { } builder
        //   that emits 1..5 with delay(500) between each
        // TODO: Create state for the collected values list
        // TODO: Use LaunchedEffect to collect the flow
        //   Add each emitted value to the state list
        // TODO: Display the collected values as they arrive
        // TODO: Show "Collecting..." while the flow is active
    }
}
