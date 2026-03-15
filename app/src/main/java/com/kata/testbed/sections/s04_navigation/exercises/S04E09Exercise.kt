package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E09Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: DisposableEffect")
        // TODO: Create a toggle to show/hide a child composable
        // TODO: In the child, use DisposableEffect to register a listener on enter
        // TODO: Clean up the listener in onDispose
        // TODO: Display the registration status and a log of register/unregister events
    }
}
