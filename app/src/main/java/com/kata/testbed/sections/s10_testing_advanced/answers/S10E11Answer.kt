package com.kata.testbed.sections.s10_testing_advanced.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val LocalAccentColor = compositionLocalOf { Color(0xFF1565C0) }

@Composable
private fun AccentLabel(text: String) {
    val accent = LocalAccentColor.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(accent.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
            .padding(12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text, color = accent, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun S10E11Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("CompositionLocal", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Custom CompositionLocal passes values implicitly through the tree.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("Default accent (blue):", fontWeight = FontWeight.Bold)
        AccentLabel("Using default LocalAccentColor")

        Spacer(modifier = Modifier.height(12.dp))

        Text("Overridden accent (green):", fontWeight = FontWeight.Bold)
        CompositionLocalProvider(LocalAccentColor provides Color(0xFF2E7D32)) {
            AccentLabel("Green accent provided here")

            Spacer(modifier = Modifier.height(8.dp))
            Text("Nested override (orange):", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalAccentColor provides Color(0xFFE65100)) {
                AccentLabel("Orange accent at nested level")
            }

            Spacer(modifier = Modifier.height(8.dp))
            AccentLabel("Back to green at this level")
        }

        Spacer(modifier = Modifier.height(12.dp))
        AccentLabel("Back to default blue outside provider")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: compositionLocalOf creates the local. CompositionLocalProvider overrides values " +
                "for a subtree. Children read via .current.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
