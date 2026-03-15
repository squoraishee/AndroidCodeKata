package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun S05E09Answer() {
    val items = remember {
        (1..10).map { "Drag item #$it" }.toMutableStateList()
    }
    var draggedIndex by remember { mutableIntStateOf(-1) }
    var dragOffsetY by remember { mutableFloatStateOf(0f) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Long-press and drag to reorder:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        ) {
            itemsIndexed(items, key = { _, item -> item }) { index, item ->
                val isDragging = index == draggedIndex

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                        .graphicsLayer {
                            // Lift the dragged item visually
                            if (isDragging) {
                                translationY = dragOffsetY
                                scaleX = 1.05f
                                scaleY = 1.05f
                                shadowElevation = 8f
                            }
                        }
                        .pointerInput(Unit) {
                            detectDragGesturesAfterLongPress(
                                onDragStart = {
                                    draggedIndex = index
                                    dragOffsetY = 0f
                                },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    dragOffsetY += dragAmount.y

                                    // Swap when dragged far enough (approx one item height)
                                    val itemHeight = 56f
                                    if (dragOffsetY > itemHeight && draggedIndex < items.size - 1) {
                                        val from = draggedIndex
                                        val to = draggedIndex + 1
                                        items[from] = items[to].also { items[to] = items[from] }
                                        draggedIndex = to
                                        dragOffsetY -= itemHeight
                                    }
                                    if (dragOffsetY < -itemHeight && draggedIndex > 0) {
                                        val from = draggedIndex
                                        val to = draggedIndex - 1
                                        items[from] = items[to].also { items[to] = items[from] }
                                        draggedIndex = to
                                        dragOffsetY += itemHeight
                                    }
                                },
                                onDragEnd = {
                                    draggedIndex = -1
                                    dragOffsetY = 0f
                                },
                                onDragCancel = {
                                    draggedIndex = -1
                                    dragOffsetY = 0f
                                }
                            )
                        },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (isDragging) 8.dp else 1.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Drag handle",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                }
            }
        }
    }
}
