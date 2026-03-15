package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E08Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a class (not a ViewModel) with:
        //   private val _count = MutableStateFlow(0)
        //   val count: StateFlow<Int> = _count.asStateFlow()
        //   fun increment() { _count.value++ }
        //   fun decrement() { _count.value-- }
        // TODO: Remember an instance of this class
        // TODO: Collect the StateFlow using collectAsState()
        // TODO: Display the count value
        // TODO: Add Increment and Decrement buttons
    }
}
