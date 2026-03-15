package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun S05E06Answer() {
    var isRefreshing by remember { mutableStateOf(false) }
    var refreshCount by remember { mutableStateOf(0) }

    // Items include a refresh counter to show they updated
    val items = remember(refreshCount) {
        (1..15).map { "Item $it (refresh #$refreshCount)" }
    }

    // LaunchedEffect triggers when isRefreshing becomes true.
    // After a simulated delay, it resets the flag and increments the counter.
    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(1500L) // simulate network call
            refreshCount++
            isRefreshing = false
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pull to Refresh (simulated):",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Button(
                onClick = { isRefreshing = true },
                enabled = !isRefreshing
            ) {
                Text("Refresh")
            }

            if (isRefreshing) {
                Spacer(modifier = Modifier.width(12.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Loading...", style = MaterialTheme.typography.bodySmall)
            }
        }

        LazyColumn(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                HorizontalDivider()
            }
        }
    }
}
