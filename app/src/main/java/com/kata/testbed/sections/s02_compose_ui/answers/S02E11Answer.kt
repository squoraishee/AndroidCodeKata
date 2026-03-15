package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun S02E11Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("ConstraintLayout (Simulated)", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Profile card using Row weights:", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))

        // Simulating constraint-like positioning with Row and weight
        // weight distributes remaining space proportionally
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar placeholder — fixed size, clipped to circle
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF7E57C2)),
                contentAlignment = Alignment.Center
            ) {
                Text("A", color = Color.White, style = MaterialTheme.typography.titleMedium)
            }

            // Name and email — takes up available space via weight(1f)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text("Alice Johnson", style = MaterialTheme.typography.titleSmall)
                Text("alice@example.com", style = MaterialTheme.typography.bodyMedium)
            }

            // Action button — fixed size, aligned to the end
            Button(onClick = { }) {
                Text("Follow")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Another example: grid-like layout with weights
        Text("Grid-like layout with weights:", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .height(60.dp)
                        .background(Color(0xFF81D4FA)),
                    contentAlignment = Alignment.Center
                ) { Text("2/3 width") }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(Color(0xFFA5D6A7)),
                    contentAlignment = Alignment.Center
                ) { Text("1/3") }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(Color(0xFFFFF176)),
                    contentAlignment = Alignment.Center
                ) { Text("1/2") }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(Color(0xFFCE93D8)),
                    contentAlignment = Alignment.Center
                ) { Text("1/2") }
            }
        }
    }
}
