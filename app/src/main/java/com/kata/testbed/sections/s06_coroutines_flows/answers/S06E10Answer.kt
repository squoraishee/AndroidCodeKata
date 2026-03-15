package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

@Composable
fun S06E10Answer() {
    val combineResults = remember { mutableListOf<String>().toMutableStateList() }
    val zipResults = remember { mutableListOf<String>().toMutableStateList() }

    val flow1 = remember {
        flow {
            listOf("A", "B", "C").forEach {
                delay(400L)
                emit(it)
            }
        }
    }

    val flow2 = remember {
        flow {
            listOf(1, 2, 3).forEach {
                delay(600L)
                emit(it)
            }
        }
    }

    LaunchedEffect(Unit) {
        // Run combine and zip in parallel to show both results
        launch {
            // combine emits whenever EITHER flow emits a new value,
            // using the latest value from the other flow.
            // Produces more emissions than zip because it does not wait for pairs.
            flow1.combine(flow2) { letter, number ->
                "$letter-$number"
            }.collect { result ->
                combineResults.add(result)
            }
        }

        launch {
            // zip pairs emissions one-to-one.
            // It waits for both flows to emit before producing a pair.
            // Number of emissions = min(flow1 count, flow2 count).
            flow1.zip(flow2) { letter, number ->
                "$letter-$number"
            }.collect { result ->
                zipResults.add(result)
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Combine vs Zip:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Flow1: A(400ms), B(400ms), C(400ms)",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Flow2: 1(600ms), 2(600ms), 3(600ms)",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "combine (latest):",
                    style = MaterialTheme.typography.titleSmall
                )
                combineResults.forEach { result ->
                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                }
                if (combineResults.isEmpty()) {
                    Text("Waiting...", style = MaterialTheme.typography.bodySmall)
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "zip (paired):",
                    style = MaterialTheme.typography.titleSmall
                )
                zipResults.forEach { result ->
                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                }
                if (zipResults.isEmpty()) {
                    Text("Waiting...", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "combine: emits on every change from either flow, using latest values. " +
                "zip: pairs emissions 1-to-1, waiting for both before emitting.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
