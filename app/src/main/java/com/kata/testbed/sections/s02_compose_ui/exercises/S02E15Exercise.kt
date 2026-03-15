package com.kata.testbed.sections.s02_compose_ui.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S02E15Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a SlotLayout composable that accepts:
        //   header: @Composable () -> Unit
        //   content: @Composable () -> Unit
        //   footer: @Composable () -> Unit
        // TODO: Inside SlotLayout, arrange header/content/footer in a Column
        //       with dividers or spacing between them
        // TODO: Call SlotLayout passing different composables for each slot
        // TODO: Show reusability by calling SlotLayout a second time with different content
    }
}
