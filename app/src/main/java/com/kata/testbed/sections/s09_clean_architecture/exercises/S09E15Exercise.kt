package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E15Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Full Vertical Slice")
        // TODO: Build a complete feature slice:
        //   Entity -> DAO -> Repository -> Use Case -> ViewModel -> UI State
        // TODO: Show data transforming as it passes through each layer
        // TODO: Display each layer's representation of the data
    }
}
