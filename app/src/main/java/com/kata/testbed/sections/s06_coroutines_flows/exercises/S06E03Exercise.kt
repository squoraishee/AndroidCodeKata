package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E03Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a mutableStateListOf<String> for the event log
        // TODO: Use LaunchedEffect to demonstrate structured concurrency:
        //   coroutineScope {
        //     launch { delay(500); log("Child 1 done") }
        //     launch { delay(1000); log("Child 2 done") }
        //     log("Parent waiting...")
        //   }
        //   log("All children completed!")
        // TODO: Display the event log in a Column of Text composables
    }
}
