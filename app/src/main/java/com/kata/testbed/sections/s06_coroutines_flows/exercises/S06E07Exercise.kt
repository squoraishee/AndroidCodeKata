package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E07Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a flow of numbers 1..20
        // TODO: Apply .map { it * it } to square each number
        // TODO: Apply .filter { it % 2 == 0 } to keep only even squares
        // TODO: Apply .take(5) to limit to 5 results
        // TODO: Collect the flow and display:
        //   - The original numbers
        //   - The transformation pipeline description
        //   - The final results
    }
}
