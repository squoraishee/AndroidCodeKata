package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E11Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Back Handler")
        // TODO: Simulate a screen with unsaved changes
        // TODO: When the user presses a "Back" button, show a confirmation dialog
        // TODO: The dialog offers "Discard" (go back) or "Stay" (cancel)
        // TODO: Track whether changes exist and whether the dialog is visible
    }
}
