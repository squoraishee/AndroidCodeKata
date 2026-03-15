package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun S02E14Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Adaptive Layout", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // BoxWithConstraints provides the available size as DpConstraints.
        // This lets you switch layouts based on how much space is available.
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val constraintsScope = this
            val isWide = constraintsScope.maxWidth > 600.dp

            Column {
                Text(
                    "maxWidth: ${constraintsScope.maxWidth}, maxHeight: ${constraintsScope.maxHeight}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    "Layout mode: ${if (isWide) "Wide (two columns)" else "Narrow (single column)"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (isWide) {
                    // Wide layout: side-by-side columns
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFFE3F2FD))
                                .padding(12.dp)
                        ) {
                            Text("Left Column", style = MaterialTheme.typography.titleSmall)
                            Text("Primary content goes here", style = MaterialTheme.typography.bodyLarge)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFFFCE4EC))
                                .padding(12.dp)
                        ) {
                            Text("Right Column", style = MaterialTheme.typography.titleSmall)
                            Text("Secondary content here", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                } else {
                    // Narrow layout: stacked vertically
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE3F2FD))
                            .padding(12.dp)
                    ) {
                        Text("Section 1", style = MaterialTheme.typography.titleSmall)
                        Text("Primary content goes here", style = MaterialTheme.typography.bodyLarge)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFCE4EC))
                            .padding(12.dp)
                    ) {
                        Text("Section 2", style = MaterialTheme.typography.titleSmall)
                        Text("Secondary content here", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
