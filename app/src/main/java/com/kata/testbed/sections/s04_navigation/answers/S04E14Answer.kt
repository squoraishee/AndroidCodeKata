package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private data class ColorItem(val id: Int, val name: String, val color: Color)

@Composable
fun S04E14Answer() {
    val items = remember {
        listOf(
            ColorItem(1, "Coral", Color(0xFFFF7F50)),
            ColorItem(2, "Teal", Color(0xFF008080)),
            ColorItem(3, "Gold", Color(0xFFFFD700)),
            ColorItem(4, "Slate", Color(0xFF6A5ACD))
        )
    }
    var selectedItem by remember { mutableStateOf<ColorItem?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        if (selectedItem == null) {
            // List view with small color thumbnails
            Text("Tap a color to expand:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { selectedItem = item }
                        .padding(8.dp)
                        .animateContentSize()
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(item.color)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        } else {
            // Detail view with expanded color box
            val item = selectedItem!!
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(item.color)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Color ID: ${item.id}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "This is the expanded detail for ${item.name}. " +
                        "The color box animated from a small thumbnail to a full-width banner.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { selectedItem = null }) {
                    Text("Back to List")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Shared element transitions animate a visual element between two screens. " +
                "In Compose, use Modifier.sharedElement() with SharedTransitionLayout (API 34+) " +
                "or simulate with animateContentSize and AnimatedContent.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
