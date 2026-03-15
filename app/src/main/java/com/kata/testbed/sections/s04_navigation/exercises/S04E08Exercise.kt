package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E08Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Lifecycle Observer")
        // TODO: Use LocalLifecycleOwner.current to get the lifecycle
        // TODO: Add a LifecycleEventObserver in a DisposableEffect
        // TODO: Track lifecycle events (ON_START, ON_RESUME, etc.) in a list state
        // TODO: Display the log of events
    }
}
