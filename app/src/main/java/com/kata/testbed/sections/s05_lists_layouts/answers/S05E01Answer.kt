package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S05E01Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Basic LazyColumn with 20 items:",
            style = MaterialTheme.typography.titleMedium
        )

        // LazyColumn only composes visible items, unlike Column which composes all.
        // A fixed height is needed here because we are inside a scrollable dialog.
        LazyColumn(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            // items(count) is the simplest form -- generates items by index
            items(20) { index ->
                Text(
                    text = "Item #$index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                if (index < 19) {
                    HorizontalDivider()
                }
            }
        }
    }
}
