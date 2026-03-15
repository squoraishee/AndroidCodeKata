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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry

@Composable
fun S06E13Answer() {
    val log = remember { mutableListOf<String>().toMutableStateList() }

    LaunchedEffect(Unit) {
        var attemptCount = 0

        // This flow intentionally throws on the third emission
        // to demonstrate retry and catch behavior.
        flow {
            attemptCount++
            log.add("Attempt #$attemptCount started")

            emit(1)
            delay(200)
            emit(2)
            delay(200)

            // Fail on first two attempts, succeed on third
            if (attemptCount <= 2) {
                throw RuntimeException("Network error on attempt #$attemptCount")
            }

            emit(3)
            delay(200)
            emit(4)
            delay(200)
            emit(5)
        }
            .onEach { value ->
                log.add("Emitted: $value")
            }
            // retry re-executes the flow from the beginning when an exception occurs.
            // The predicate can inspect the exception and decide whether to retry.
            .retry(2) { cause ->
                log.add("Retrying after: ${cause.message}")
                delay(300) // back-off delay before retry
                true       // return true to retry, false to propagate the exception
            }
            // catch handles exceptions that were not retried or that exceed retry count.
            // It can emit fallback values downstream.
            .catch { exception ->
                log.add("Caught unrecoverable: ${exception.message}")
                emit(-1) // fallback value
            }
            .collect { value ->
                log.add("Collected: $value")
            }

        log.add("")
        log.add("Flow completed. Total attempts: $attemptCount")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Flow Error Recovery:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        log.forEach { entry ->
            if (entry.isEmpty()) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            } else {
                val color = when {
                    entry.startsWith("Retrying") -> MaterialTheme.colorScheme.tertiary
                    entry.startsWith("Caught") -> MaterialTheme.colorScheme.error
                    entry.startsWith("Collected") -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurface
                }
                Text(
                    text = entry,
                    style = MaterialTheme.typography.bodyMedium,
                    color = color,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}
