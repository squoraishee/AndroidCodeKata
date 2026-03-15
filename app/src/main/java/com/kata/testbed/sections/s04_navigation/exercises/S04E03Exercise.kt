package com.kata.testbed.sections.s04_navigation.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S04E03Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Type-Safe Routes")
        // TODO: Define route classes: data object HomeRoute, data class DetailRoute(val id: String)
        // TODO: Use a state variable of a common Route sealed interface
        // TODO: Navigate by setting state to a route object (no string parsing)
        // TODO: Show how type safety prevents invalid route arguments
    }
}
