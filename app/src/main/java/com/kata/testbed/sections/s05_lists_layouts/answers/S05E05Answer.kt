package com.kata.testbed.sections.s05_lists_layouts.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

private data class GridItem(val icon: ImageVector, val label: String)

@Composable
fun S05E05Answer() {
    val gridItems = listOf(
        GridItem(Icons.Filled.Home, "Home"),
        GridItem(Icons.Filled.Favorite, "Favorites"),
        GridItem(Icons.Filled.Settings, "Settings"),
        GridItem(Icons.Filled.Email, "Email"),
        GridItem(Icons.Filled.Star, "Starred"),
        GridItem(Icons.Filled.AccountCircle, "Profile"),
        GridItem(Icons.Filled.Notifications, "Alerts"),
        GridItem(Icons.Filled.Share, "Share"),
        GridItem(Icons.Filled.Build, "Tools"),
        GridItem(Icons.Filled.Call, "Call"),
        GridItem(Icons.Filled.Info, "Info"),
        GridItem(Icons.Filled.Lock, "Security")
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "2-Column Grid Layout:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // LazyVerticalGrid arranges items in a grid pattern.
        // GridCells.Fixed(2) creates exactly 2 columns.
        // GridCells.Adaptive(minSize) would create as many columns as fit.
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(gridItems) { item ->
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
