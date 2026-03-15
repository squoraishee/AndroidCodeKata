package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class AnimatedItem(val id: Int, val text: String)

@Composable
fun S05E10Answer() {
    var nextId by remember { mutableIntStateOf(6) }
    val items = remember {
        (1..5).map { AnimatedItem(it, "Item $it") }.toMutableStateList()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Animated Item Add/Remove:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Button(onClick = {
                items.add(0, AnimatedItem(nextId, "Item $nextId"))
                nextId++
            }) {
                Text("Add Item")
            }

            Button(
                onClick = { if (items.isNotEmpty()) items.removeLast() },
                enabled = items.isNotEmpty()
            ) {
                Text("Remove Last")
            }
        }

        LazyColumn(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
        ) {
            // key ensures LazyColumn tracks each item by its unique ID.
            // animateItem() animates the layout change when items are added or removed.
            items(items, key = { it.id }) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem() // smooth insertion/removal animation
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    IconButton(onClick = { items.remove(item) }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Remove"
                        )
                    }
                }
                HorizontalDivider()
            }
        }
    }
}
