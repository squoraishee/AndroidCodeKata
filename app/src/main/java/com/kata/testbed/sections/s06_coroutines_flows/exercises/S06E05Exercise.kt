package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E05Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create state for count and isRunning
        // TODO: Create a coroutineScope with rememberCoroutineScope()
        // TODO: Store the Job reference so you can cancel it
        // TODO: Start button: launch a coroutine that increments count every 500ms
        //   Use while(isActive) to be cooperative with cancellation
        // TODO: Cancel button: call job.cancel()
        // TODO: Display the count and running status
    }
}
