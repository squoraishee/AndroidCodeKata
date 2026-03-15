package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

private data class ListItem(val id: Int, val label: String)

@Composable
private fun ItemRow(item: ListItem) {
    // This counter tracks how many times this composable was composed.
    // With key(), Compose matches items by ID and avoids recreating them on reorder.
    val composeCount = remember { mutableIntStateOf(0) }
    composeCount.intValue++

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp)
    ) {
        Text(text = item.label, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "composed: ${composeCount.intValue}x",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun S03E12Answer() {
    var items by remember {
        mutableStateOf(
            listOf(
                ListItem(1, "Apple"),
                ListItem(2, "Banana"),
                ListItem(3, "Cherry"),
                ListItem(4, "Date"),
                ListItem(5, "Elderberry")
            )
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            Button(onClick = { items = items.shuffled() }) {
                Text("Shuffle")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                items = items.sortedBy { it.label }
            }) {
                Text("Sort")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("With key(item.id):", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(4.dp))

        items.forEach { item ->
            // key() tells Compose to identify this slot by item.id.
            // When items reorder, Compose moves existing compositions rather than recreating.
            key(item.id) {
                ItemRow(item = item)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "key() helps Compose track items by a stable identity. " +
                "On reorder, compositions are moved instead of destroyed and recreated, " +
                "preserving internal state like remember values.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
