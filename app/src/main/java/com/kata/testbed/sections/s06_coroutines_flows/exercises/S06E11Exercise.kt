package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E11Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a simulated callback-based "sensor" API
        //   that calls a callback every 500ms with a new reading
        // TODO: Use callbackFlow { } to convert it into a Flow:
        //   callbackFlow {
        //     val callback = { value -> trySend(value) }
        //     // register callback
        //     awaitClose { /* cleanup */ }
        //   }
        // TODO: Collect the flow and display the sensor readings
        // TODO: Add a Start/Stop button to control collection
    }
}
