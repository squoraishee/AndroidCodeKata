package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class AnimatedScreen(val index: Int, val title: String, val content: String)

@Composable
fun S04E13Answer() {
    val screens = remember {
        listOf(
            AnimatedScreen(0, "Welcome", "Welcome to the animated navigation demo!"),
            AnimatedScreen(1, "Features", "Slide transitions between screens create smooth UX."),
            AnimatedScreen(2, "Summary", "This is the final screen. Navigate back to restart.")
        )
    }
    var currentIndex by remember { mutableIntStateOf(0) }
    var navigatingForward by remember { mutableIntStateOf(1) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Screen ${currentIndex + 1} of ${screens.size}",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        // AnimatedContent transitions between composables when the target state changes.
        // The transitionSpec defines enter and exit animations.
        AnimatedContent(
            targetState = currentIndex,
            transitionSpec = {
                if (navigatingForward > 0) {
                    // Forward: slide in from right, slide out to left
                    slideInHorizontally { width -> width } togetherWith
                        slideOutHorizontally { width -> -width }
                } else {
                    // Backward: slide in from left, slide out to right
                    slideInHorizontally { width -> -width } togetherWith
                        slideOutHorizontally { width -> width }
                }
            },
            label = "screenTransition"
        ) { targetIndex ->
            val screen = screens[targetIndex]
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(screen.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(screen.content, style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            OutlinedButton(
                onClick = {
                    navigatingForward = -1
                    currentIndex--
                },
                enabled = currentIndex > 0
            ) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    navigatingForward = 1
                    currentIndex++
                },
                enabled = currentIndex < screens.lastIndex
            ) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "AnimatedContent transitions between composables with enter/exit animations. " +
                "In Compose Navigation, use AnimatedNavHost for the same effect. " +
                "Common transitions: slideInHorizontally, fadeIn, scaleIn.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
