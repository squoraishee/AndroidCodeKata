package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E04Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create state for log messages
        // TODO: Use LaunchedEffect to demonstrate exception handling:
        // TODO: First, show try/catch around a failing coroutine
        // TODO: Then, show supervisorScope where one child fails
        //   but the other continues
        //   supervisorScope {
        //     launch { throw Exception("Task A failed") }
        //     launch { delay(500); log("Task B succeeded") }
        //   }
        // TODO: Display the log showing that Task B completed despite Task A failing
    }
}
