package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E10Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: LaunchedEffect")
        // TODO: Create buttons to select different item IDs
        // TODO: Use LaunchedEffect(selectedId) to simulate loading data
        // TODO: Show a loading indicator during the delay
        // TODO: Display the loaded data after the delay completes
    }
}
