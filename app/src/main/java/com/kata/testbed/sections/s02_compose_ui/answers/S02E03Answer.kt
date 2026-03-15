package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun S02E03Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Box Layout", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Box stacks children on top of each other (like a FrameLayout).
        // Each child can be positioned with Alignment via Modifier.align().
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFFE0E0E0))
        ) {
            // First child draws at the bottom of the z-order
            Text(
                "TopStart",
                modifier = Modifier.align(Alignment.TopStart).padding(4.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                "Center",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                "BottomEnd",
                modifier = Modifier.align(Alignment.BottomEnd).padding(4.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Overlapping demonstration: later children draw on top
        Text("Overlapping (later = on top):", style = MaterialTheme.typography.bodyMedium)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            // Background layer
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0xFF81D4FA))
            )
            // Middle layer
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(8.dp)
                    .background(Color(0xFFFFF176))
                    .padding(8.dp)
            ) {
                Text("Layer 2", style = MaterialTheme.typography.bodyLarge)
            }
            // Top layer
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(8.dp)
                    .background(Color(0xFFA5D6A7))
                    .padding(8.dp)
            ) {
                Text("Layer 3", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
