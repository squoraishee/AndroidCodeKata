package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E06Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: ViewModel StateFlow")
        // TODO: Create a class CounterStateHolder with a private MutableStateFlow<Int>
        // TODO: Expose it as a public StateFlow<Int>
        // TODO: Add an increment() method
        // TODO: In the composable, remember the state holder
        // TODO: Collect the flow with collectAsState() and display the value
        // TODO: Add a button that calls increment()
    }
}
