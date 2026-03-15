package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

@Composable
fun S06E07Answer() {
    val results = remember { mutableListOf<String>().toMutableStateList() }

    LaunchedEffect(Unit) {
        // Operators transform the flow without collecting it.
        // They are "intermediate" -- they return a new Flow.
        val transformed = (1..20).asFlow()
            .map { it * it }              // square: 1, 4, 9, 16, 25, ...
            .filter { it % 2 == 0 }       // even only: 4, 16, 36, 64, 100, ...
            .take(5)                       // first 5 results

        results.add("Source: 1..20")
        results.add("Pipeline: map(x*x) -> filter(even) -> take(5)")
        results.add("")

        val collected = transformed.toList()
        collected.forEachIndexed { index, value ->
            // Show the original number that produced this value
            val original = Math.sqrt(value.toDouble()).toInt()
            results.add("$original -> squared = $value (even, kept)")
        }

        results.add("")
        results.add("Final results: $collected")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Flow Operators:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        results.forEach { line ->
            if (line.isEmpty()) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            } else {
                Text(
                    text = line,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}
