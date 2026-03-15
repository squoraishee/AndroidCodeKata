package com.kata.testbed.sections.s10_testing_advanced.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S10E11Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: CompositionLocal")
        // TODO: Create a custom CompositionLocal for an accent color
        // TODO: Provide different values at different tree levels
        // TODO: Display composables that read the CompositionLocal value
    }
}
