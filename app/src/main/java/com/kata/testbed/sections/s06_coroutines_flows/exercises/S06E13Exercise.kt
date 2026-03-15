package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E13Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a flow that emits values but throws on certain items
        //   e.g., emit 1, 2, throw on 3, emit 4, 5
        // TODO: Use .retry(2) to retry the flow on failure
        // TODO: Use .catch { emit(-1) } to recover from unrecoverable errors
        // TODO: Collect the flow and display both successful values and error handling
        // TODO: Show a log of what happened (retries, catches)
    }
}
