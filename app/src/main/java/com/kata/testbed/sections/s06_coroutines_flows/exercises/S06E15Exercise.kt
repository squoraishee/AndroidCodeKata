package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E15Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a counter class that accepts a CoroutineDispatcher
        //   class TestableCounter(private val dispatcher: CoroutineDispatcher)
        //   The counter should count up every 100ms on the given dispatcher
        // TODO: Show how injecting the dispatcher makes the code testable:
        //   - Production: use Dispatchers.Default
        //   - Testing: could use a TestDispatcher (concept demo)
        // TODO: Run the counter and display values
        // TODO: Add explanatory text about why dispatcher injection matters
    }
}
