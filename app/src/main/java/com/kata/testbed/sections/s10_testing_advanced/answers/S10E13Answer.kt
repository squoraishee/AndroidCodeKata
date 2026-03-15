package com.kata.testbed.sections.s10_testing_advanced.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
private fun CircularLayout(
    radius: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val layoutSize = (radius * 2).toInt() + (placeables.maxOfOrNull { maxOf(it.width, it.height) } ?: 0)

        layout(layoutSize, layoutSize) {
            val centerX = layoutSize / 2
            val centerY = layoutSize / 2
            val angleStep = 2 * Math.PI / placeables.size

            placeables.forEachIndexed { index, placeable ->
                val angle = angleStep * index - Math.PI / 2
                val x = centerX + (radius * cos(angle)).toInt() - placeable.width / 2
                val y = centerY + (radius * sin(angle)).toInt() - placeable.height / 2
                placeable.place(x, y)
            }
        }
    }
}

@Composable
private fun ColorDot(color: Color, label: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(label, color = Color.White, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun S10E13Answer() {
    val colors = listOf(
        Color(0xFFC62828) to "R",
        Color(0xFF2E7D32) to "G",
        Color(0xFF1565C0) to "B",
        Color(0xFFE65100) to "O",
        Color(0xFF6A1B9A) to "P",
        Color(0xFF00838F) to "C"
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Custom Layout", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("A custom Layout composable that positions children in a circle.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            CircularLayout(radius = 80f) {
                colors.forEach { (color, label) ->
                    ColorDot(color = color, label = label)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Layout { measurables, constraints -> } measures children, then places them " +
                "at calculated positions. Use trigonometry for circular placement.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
