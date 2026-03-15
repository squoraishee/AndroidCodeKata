package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

// Simulated callback-based sensor API.
// In real code, this might be a location listener, Bluetooth scanner, etc.
private class FakeSensor {
    private var callback: ((Double) -> Unit)? = null
    private var running = false

    fun registerListener(cb: (Double) -> Unit) {
        callback = cb
        running = true
    }

    fun unregisterListener() {
        callback = null
        running = false
    }

    suspend fun startEmitting() {
        var reading = 20.0
        while (running) {
            delay(500L)
            reading += (-1..3).random() * 0.5
            callback?.invoke(reading)
        }
    }
}

// callbackFlow bridges callback-based APIs into the Flow world.
// It creates a channel internally and sends values via trySend.
private fun sensorFlow(): Flow<Double> = callbackFlow {
    val sensor = FakeSensor()

    sensor.registerListener { reading ->
        trySend(reading) // non-blocking send into the channel
    }

    // Launch the sensor emission loop in this coroutine scope
    launch { sensor.startEmitting() }

    // awaitClose is called when the flow collector cancels.
    // Use it to clean up resources (unregister listeners, close connections).
    awaitClose {
        sensor.unregisterListener()
    }
}

@Composable
fun S06E11Answer() {
    val readings = remember { mutableListOf<String>().toMutableStateList() }
    var isRunning by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var collectionJob by remember { mutableStateOf<Job?>(null) }

    // Clean up on disposal
    DisposableEffect(Unit) {
        onDispose { collectionJob?.cancel() }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "callbackFlow (Sensor Simulation):",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Button(
            onClick = {
                if (isRunning) {
                    collectionJob?.cancel()
                    isRunning = false
                    readings.add("--- Stopped ---")
                } else {
                    isRunning = true
                    readings.clear()
                    readings.add("--- Started ---")
                    collectionJob = scope.launch {
                        sensorFlow().collect { reading ->
                            readings.add("Temp: ${"%.1f".format(reading)}C")
                        }
                    }
                }
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(if (isRunning) "Stop Sensor" else "Start Sensor")
        }

        Text(
            text = "Readings (${readings.size}):",
            style = MaterialTheme.typography.titleSmall
        )

        LazyColumn(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            items(readings) { reading ->
                Text(
                    text = reading,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}
