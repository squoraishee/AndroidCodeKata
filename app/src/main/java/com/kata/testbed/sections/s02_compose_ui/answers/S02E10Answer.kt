package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun S02E10Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Canvas Drawing", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Canvas provides a DrawScope where you can issue draw commands
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // drawRect draws a filled or stroked rectangle
            drawRect(
                color = Color(0xFFE3F2FD),
                size = size // fills the entire canvas
            )

            // drawCircle places a circle at the given center
            drawCircle(
                color = Color(0xFF2196F3),
                radius = 50f,
                center = Offset(100f, 100f)
            )

            // Stroke style creates an outline instead of a fill
            drawCircle(
                color = Color(0xFF1565C0),
                radius = 50f,
                center = Offset(100f, 100f),
                style = Stroke(width = 4f)
            )

            // drawRect with position and size for a specific rectangle
            drawRect(
                color = Color(0xFF4CAF50),
                topLeft = Offset(200f, 50f),
                size = Size(150f, 100f)
            )

            // drawLine from point A to point B
            drawLine(
                color = Color(0xFFF44336),
                start = Offset(0f, size.height),
                end = Offset(size.width, 0f),
                strokeWidth = 3f
            )

            // Another line
            drawLine(
                color = Color(0xFFFF9800),
                start = Offset(0f, 0f),
                end = Offset(size.width, size.height),
                strokeWidth = 3f
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Blue circle, green rect, diagonal lines",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
