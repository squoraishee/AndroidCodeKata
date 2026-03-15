package com.kata.testbed.sections.s06_coroutines_flows.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S06E14Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a Channel<String>(capacity = Channel.BUFFERED)
        // TODO: In LaunchedEffect, launch a producer coroutine that
        //   sends "Message 1" through "Message 5" with delays
        // TODO: Launch a consumer coroutine that receives from the channel
        //   and adds messages to a state list
        // TODO: Display the received messages as they arrive
        // TODO: Close the channel when done sending
    }
}
