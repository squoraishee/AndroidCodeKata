package com.kata.testbed.sections.s02_compose_ui.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S02E04Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Create a Text with modifier chain: padding -> background -> padding
        //       to show that order matters (background goes between paddings)
        // TODO: Create a Box with: clip(RoundedCornerShape) -> background -> padding
        // TODO: Create a Box with: border -> padding -> background
        //       to show border outside vs background inside
        // TODO: Create a Box with: fillMaxWidth -> height -> background
    }
}
