package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E02Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create state for result1, result2, and elapsedTime
        // TODO: Use LaunchedEffect to run two async tasks concurrently:
        //   val deferred1 = async { delay(1000); "User data" }
        //   val deferred2 = async { delay(1500); "Settings data" }
        // TODO: Await both results
        // TODO: Measure elapsed time using System.currentTimeMillis()
        // TODO: Display both results and total time
        // TODO: Note: sequential would take 2500ms, concurrent takes ~1500ms
    }
}
