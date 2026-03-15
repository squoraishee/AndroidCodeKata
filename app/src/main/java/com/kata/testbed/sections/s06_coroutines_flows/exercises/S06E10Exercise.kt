package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E10Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create flow1 that emits "A", "B", "C" with 1000ms delay
        // TODO: Create flow2 that emits 1, 2, 3 with 1500ms delay
        // TODO: Use combine(flow1, flow2) to get the latest from each
        // TODO: Use flow1.zip(flow2) to pair them one-to-one
        // TODO: Collect both in separate LaunchedEffects
        // TODO: Display combined results and zipped results side by side
    }
}
