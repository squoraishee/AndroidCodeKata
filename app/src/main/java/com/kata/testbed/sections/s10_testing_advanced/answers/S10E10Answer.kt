package com.kata.testbed.sections.s10_testing_advanced.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
private fun MeasuredBox(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    Column {
        Box(
            modifier = modifier
                .background(color.copy(alpha = 0.2f))
                .onSizeChanged { size = it }
                .padding(8.dp)
        ) {
            content()
        }
        val widthDp = with(density) { size.width.toDp() }
        val heightDp = with(density) { size.height.toDp() }
        Text(
            "$label: ${size.width}x${size.height}px ($widthDp x $heightDp)",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun S10E10Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Custom Modifier", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Modifier.onSizeChanged measures composable dimensions after layout.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        MeasuredBox(label = "Small box", color = Color(0xFF1565C0), modifier = Modifier.size(80.dp)) {
            Text("80dp", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        MeasuredBox(label = "Wide box", color = Color(0xFF2E7D32), modifier = Modifier.fillMaxWidth().height(40.dp)) {
            Text("Full width x 40dp", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        MeasuredBox(label = "Content-sized", color = Color(0xFFE65100)) {
            Text("This box wraps its content\nwith multiple lines\nof text inside", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Modifier.onSizeChanged { } reports pixel dimensions after layout. " +
                "Use LocalDensity to convert px to dp.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
