package com.kata.testbed.sections.s02_compose_ui.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun S02E02Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Column and Row", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Column stacks children vertically; spacedBy adds consistent gaps
        Text("Column with spacedBy(8.dp):", style = MaterialTheme.typography.bodyMedium)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Item 1", style = MaterialTheme.typography.bodyLarge)
            Text("Item 2", style = MaterialTheme.typography.bodyLarge)
            Text("Item 3", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Row arranges children horizontally; SpaceEvenly distributes space equally
        Text("Row with SpaceEvenly:", style = MaterialTheme.typography.bodyMedium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("A", style = MaterialTheme.typography.bodyLarge)
            Text("B", style = MaterialTheme.typography.bodyLarge)
            Text("C", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CenterVertically aligns items along the vertical center
        Text("Row with CenterVertically:", style = MaterialTheme.typography.bodyMedium)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Small", fontSize = 12.sp)
            Text("Medium", fontSize = 18.sp)
            Text("Large", fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nesting: Row inside Column shows how layouts compose together
        Text("Nested layouts:", style = MaterialTheme.typography.bodyMedium)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("Row 1, Col 1", style = MaterialTheme.typography.bodyLarge)
                Text("Row 1, Col 2", style = MaterialTheme.typography.bodyLarge)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("Row 2, Col 1", style = MaterialTheme.typography.bodyLarge)
                Text("Row 2, Col 2", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
