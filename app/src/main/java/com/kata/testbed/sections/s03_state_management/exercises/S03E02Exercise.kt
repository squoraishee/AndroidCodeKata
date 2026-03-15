package com.kata.testbed.sections.s03_state_management.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E02Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: State Hoisting")
        // TODO: Create a state variable for count in this parent composable
        // TODO: Create a stateless child composable that receives count and onIncrement
        // TODO: The child displays the count and has a button that calls onIncrement
    }
}
