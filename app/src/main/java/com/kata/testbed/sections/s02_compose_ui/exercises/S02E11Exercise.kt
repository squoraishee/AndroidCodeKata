package com.kata.testbed.sections.s02_compose_ui.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S02E11Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // NOTE: We simulate ConstraintLayout using nested Row/Column with weights
        // TODO: Create a "profile card" layout with:
        //   - An icon/avatar placeholder on the left (Box with background)
        //   - Name and email stacked vertically to the right of the avatar
        //   - An action button aligned to the far right
        // TODO: Use Row with weight modifiers to distribute space
        // TODO: Use vertical alignment to center items
    }
}
