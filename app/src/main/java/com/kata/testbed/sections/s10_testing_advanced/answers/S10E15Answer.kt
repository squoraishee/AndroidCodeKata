package com.kata.testbed.sections.s10_testing_advanced.answers

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun S10E15Answer() {
    val scope = rememberCoroutineScope()

    val offsetXAnim = remember { Animatable(0f) }
    val scaleAnim = remember { Animatable(1f) }
    val colorProgress = remember { Animatable(0f) }

    var stepLabel by remember { mutableStateOf("Idle") }
    var isAnimating by remember { mutableStateOf(false) }

    val startColor = Color(0xFF1565C0)
    val endColor = Color(0xFFC62828)
    val currentColor = lerp(startColor, endColor, colorProgress.value)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Animation Choreography", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Animatable with coroutines for sequenced, coordinated animations.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetXAnim.value.toInt(), 0) }
                    .scale(scaleAnim.value)
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(currentColor)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("Step: $stepLabel", fontWeight = FontWeight.Bold)
        Text("Offset X: ${"%.0f".format(offsetXAnim.value)}")
        Text("Scale: ${"%.2f".format(scaleAnim.value)}")
        Text("Color: ${if (colorProgress.value < 0.5f) "Blue" else "Red"}")

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (isAnimating) return@Button
                isAnimating = true
                scope.launch {
                    // Step 1: Slide right
                    stepLabel = "1. Sliding right"
                    offsetXAnim.animateTo(
                        targetValue = 500f,
                        animationSpec = tween(durationMillis = 600)
                    )

                    // Step 2: Scale up
                    stepLabel = "2. Scaling up"
                    scaleAnim.animateTo(
                        targetValue = 1.5f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )

                    // Step 3: Change color
                    stepLabel = "3. Changing color"
                    colorProgress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 400)
                    )

                    // Step 4: Scale down and slide back
                    stepLabel = "4. Returning"
                    launch { scaleAnim.animateTo(1f, tween(500)) }
                    launch { colorProgress.animateTo(0f, tween(500)) }
                    offsetXAnim.animateTo(0f, tween(500))

                    stepLabel = "Done"
                    isAnimating = false
                }
            },
            enabled = !isAnimating
        ) {
            Text("Run Sequence")
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Animatable.animateTo() is a suspend function. Sequential calls run in order. " +
                "Parallel animations use nested launch { } blocks. spring() and tween() control easing.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
