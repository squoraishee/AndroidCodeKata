package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E01Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a state variable for the result string (initially null or empty)
        // TODO: Create a state variable for isLoading (initially true)
        // TODO: Write a suspend function that calls delay(1000) and returns "Hello from coroutine!"
        // TODO: Use LaunchedEffect(Unit) to call the suspend function
        // TODO: Update isLoading and result state when it completes
        // TODO: Show CircularProgressIndicator while loading
        // TODO: Show the result Text when loaded
    }
}
