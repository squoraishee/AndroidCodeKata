package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun S02E01Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hello Text", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // fontSize and fontWeight control size and boldness
        Text(
            "Bold 24sp Text",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))

        // color and fontStyle for visual styling
        Text(
            "Blue Italic Text",
            color = Color.Blue,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(4.dp))

        // letterSpacing spreads characters apart
        Text(
            "S P A C E D",
            letterSpacing = 4.sp
        )
        Spacer(modifier = Modifier.height(4.dp))

        // textDecoration adds underline or line-through
        Text(
            "Underlined Text",
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(4.dp))

        // MaterialTheme.typography provides a consistent type scale
        Text(
            "Headline Medium",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            "Body Large",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            "Label Small",
            style = MaterialTheme.typography.labelSmall
        )
    }
}
