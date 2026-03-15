package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun S02E04Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Modifier Chains", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Modifier order matters: each modifier wraps the result of the previous one.
        // Here, outer padding -> background -> inner padding creates a colored "frame"
        Text("Order matters:", style = MaterialTheme.typography.bodyMedium)
        Text(
            "Padded Background",
            modifier = Modifier
                .padding(4.dp)              // Outer spacing (transparent)
                .background(Color(0xFF81D4FA)) // Background color
                .padding(12.dp),            // Inner spacing (inside the background)
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Clip rounds the corners BEFORE background fills the area
        Text("Clip + Background:", style = MaterialTheme.typography.bodyMedium)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFA5D6A7))
                .padding(16.dp)
        ) {
            Text("Rounded corners", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Border draws an outline; padding inside pushes content away from it
        Text("Border + Padding + Background:", style = MaterialTheme.typography.bodyMedium)
        Box(
            modifier = Modifier
                .border(2.dp, Color(0xFFF44336), RoundedCornerShape(8.dp))
                .padding(8.dp)
                .background(Color(0xFFFFF9C4))
                .padding(8.dp)
        ) {
            Text("Bordered box", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // fillMaxWidth + height + background creates a colored bar
        Text("fillMaxWidth + height:", style = MaterialTheme.typography.bodyMedium)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFCE93D8))
        )
    }
}
