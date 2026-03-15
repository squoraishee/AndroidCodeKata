package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E11Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Offline-First")
        // TODO: Create a CacheRepository and a NetworkRepository
        // TODO: Create a use case that reads from cache first
        // TODO: If network available, refresh cache; if not, use stale cache
        // TODO: Display the data source (cache/network) and freshness
    }
}
