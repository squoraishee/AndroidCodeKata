package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// This class accepts a CoroutineDispatcher as a constructor parameter.
// In production, pass Dispatchers.Default or Dispatchers.IO.
// In tests, pass a TestCoroutineDispatcher to control time precisely.
private class TestableCounter(private val dispatcher: CoroutineDispatcher) {
    suspend fun countTo(target: Int, onTick: (Int) -> Unit) {
        withContext(dispatcher) {
            for (i in 1..target) {
                delay(100L) // in tests, TestDispatcher can advance time instantly
                onTick(i)
            }
        }
    }
}

@Composable
fun S06E15Answer() {
    val log = remember { mutableListOf<String>().toMutableStateList() }
    val scope = rememberCoroutineScope()
    var isRunning by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Testing Coroutines:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Why inject a dispatcher?",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "By accepting CoroutineDispatcher as a parameter instead of hardcoding " +
                "Dispatchers.Default, this class becomes testable. In tests, you can inject " +
                "a TestCoroutineDispatcher that lets you control virtual time -- " +
                "no real delays, instant test execution.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        HorizontalDivider()

        Text(
            text = "Production code (Dispatchers.Default):",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )

        Text(
            text = "val counter = TestableCounter(Dispatchers.Default)\ncounter.countTo(5) { println(it) }",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Test code (TestDispatcher):",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )

        Text(
            text = "val testDispatcher = StandardTestDispatcher()\nval counter = TestableCounter(testDispatcher)\n" +
                "// advance time instantly:\ntestDispatcher.scheduler.advanceTimeBy(500)\n// all 5 ticks complete immediately",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        HorizontalDivider()

        Button(
            onClick = {
                if (!isRunning) {
                    isRunning = true
                    log.clear()
                    log.add("Starting counter with Dispatchers.Default...")

                    // Production usage: real dispatcher with real delays
                    val counter = TestableCounter(Dispatchers.Default)
                    scope.launch {
                        counter.countTo(5) { tick ->
                            log.add("Tick: $tick")
                        }
                        log.add("Counter finished!")
                        isRunning = false
                    }
                }
            },
            enabled = !isRunning,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Run Counter Demo")
        }

        log.forEach { entry ->
            Text(
                text = entry,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 1.dp)
            )
        }
    }
}
