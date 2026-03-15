package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun S02E13Answer() {
    // Typography defines the full set of text styles used across the app.
    // Override specific styles to create a consistent type scale.
    val customTypography = Typography(
        headlineLarge = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = (-0.5).sp
        ),
        headlineMedium = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        ),
        bodyLarge = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.5.sp,
            lineHeight = 26.sp
        ),
        bodyMedium = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.25.sp
        ),
        labelSmall = TextStyle(
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.5.sp
        )
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Typography Scale", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Apply the custom typography to a sub-tree
        MaterialTheme(typography = customTypography) {
            Column {
                Text(
                    "Headline Large",
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Headline Medium",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Body Large — with wider letter spacing and taller line height for comfortable reading.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Body Medium — used for secondary content and descriptions.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "LABEL SMALL — GOOD FOR CAPTIONS",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
