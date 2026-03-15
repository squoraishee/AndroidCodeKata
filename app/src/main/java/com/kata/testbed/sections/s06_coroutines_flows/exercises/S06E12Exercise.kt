package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E12Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a state for the search query text
        // TODO: Create a MutableStateFlow<String> for the query
        // TODO: Create an OutlinedTextField bound to the query state
        // TODO: Update the StateFlow whenever text changes
        // TODO: Use LaunchedEffect to collect the flow with:
        //   .debounce(300)
        //   .filter { it.length >= 2 }
        //   .distinctUntilChanged()
        // TODO: Simulate search results based on the debounced query
        // TODO: Display the search results
    }
}
