package com.kata.testbed.sections.s06_coroutines_flows.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun S06E14Answer() {
    val log = remember { mutableListOf<String>().toMutableStateList() }

    LaunchedEffect(Unit) {
        // Channel provides a way for coroutines to communicate by sending and receiving values.
        // Unlike Flow, channels are "hot" -- they exist independently of collectors.
        // BUFFERED capacity means sends won't suspend until the buffer is full.
        val channel = Channel<String>(capacity = Channel.BUFFERED)

        // Producer: sends messages into the channel
        launch {
            for (i in 1..5) {
                val message = "Message #$i"
                log.add("Producer: sending $message")
                channel.send(message)
                delay(400L)
            }
            // Close the channel to signal no more messages will be sent.
            // The consumer's for loop will terminate.
            channel.close()
            log.add("Producer: channel closed")
        }

        // Consumer: receives messages from the channel
        launch {
            // Iterating over a channel with for-in receives values until the channel is closed
            for (message in channel) {
                log.add("Consumer: received $message")
                delay(200L) // simulate processing time
            }
            log.add("Consumer: channel exhausted, done")
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Channel Communication:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        log.forEach { entry ->
            val color = when {
                entry.startsWith("Producer") -> MaterialTheme.colorScheme.primary
                entry.startsWith("Consumer") -> MaterialTheme.colorScheme.tertiary
                else -> MaterialTheme.colorScheme.onSurface
            }
            Text(
                text = entry,
                style = MaterialTheme.typography.bodyMedium,
                color = color,
                modifier = Modifier.padding(vertical = 1.dp)
            )
        }

        Text(
            text = "Channels are hot streams for coroutine-to-coroutine communication. " +
                "Unlike Flow, a Channel has send/receive semantics and exists independently of collectors.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
