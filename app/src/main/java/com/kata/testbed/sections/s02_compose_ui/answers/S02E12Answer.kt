package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun S02E12Answer() {
    // Define a completely custom color scheme
    // lightColorScheme provides Material 3 defaults; we override specific roles
    val customColors = lightColorScheme(
        primary = Color(0xFF6750A4),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFE8DEF8),
        secondary = Color(0xFF625B71),
        surface = Color(0xFFFFFBFE),
        onSurface = Color(0xFF1C1B1F),
        background = Color(0xFFF6EDFF)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Custom Theme", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // MaterialTheme scopes the custom colors to its children
        // Everything inside inherits the custom color scheme
        MaterialTheme(colorScheme = customColors) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // These text colors come from the custom theme
                    Text(
                        "Themed heading",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Body text uses onSurface color",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Buttons automatically pick up the theme's primary color
                    Button(onClick = { }) {
                        Text("Themed Button")
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            "Primary Container surface",
                            modifier = Modifier.padding(12.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
