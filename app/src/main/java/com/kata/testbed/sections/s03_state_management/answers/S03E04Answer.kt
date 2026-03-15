package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S03E04Answer() {
    val fruits = remember {
        listOf("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape", "Honeydew")
    }
    var query by remember { mutableStateOf("") }

    // derivedStateOf only recalculates when its inputs (query, fruits) change.
    // Without it, the filter would run on every recomposition even if query hasn't changed.
    val filteredFruits by remember {
        derivedStateOf {
            if (query.isBlank()) fruits
            else fruits.filter { it.contains(query, ignoreCase = true) }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search fruits") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Showing ${filteredFruits.size} of ${fruits.size} items",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))

        filteredFruits.forEach { fruit ->
            Text(text = fruit, modifier = Modifier.padding(vertical = 2.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "derivedStateOf caches the computed value and only recalculates " +
                "when the state it reads changes. This avoids redundant filtering.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
