package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Slot-based API: the composable accepts other composables as parameters.
// This is how Scaffold, Card, and other Material components work internally.
@Composable
private fun SlotLayout(
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
    footer: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        header()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        content()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        footer()
    }
}

@Composable
fun S02E15Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Slot-Based Component", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // First usage: article layout
        SlotLayout(
            header = {
                Text("Breaking News", style = MaterialTheme.typography.headlineSmall)
            },
            content = {
                Text(
                    "Slot-based APIs let you inject any composable into " +
                        "predefined locations. The parent controls layout; " +
                        "the caller controls content.",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            footer = {
                Text("Published March 2026", style = MaterialTheme.typography.labelMedium)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Second usage: same component, different content — demonstrating reusability
        SlotLayout(
            header = {
                Text("User Profile", style = MaterialTheme.typography.headlineSmall)
            },
            content = {
                Column {
                    Text("Name: Alice Johnson", style = MaterialTheme.typography.bodyLarge)
                    Text("Role: Developer", style = MaterialTheme.typography.bodyLarge)
                    Text("Team: Android", style = MaterialTheme.typography.bodyLarge)
                }
            },
            footer = {
                Text("Last active: today", style = MaterialTheme.typography.labelMedium)
            }
        )
    }
}
