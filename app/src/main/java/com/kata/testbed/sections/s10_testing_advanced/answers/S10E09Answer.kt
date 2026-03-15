package com.kata.testbed.sections.s10_testing_advanced.answers

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun S10E09Answer() {
    val testCode = """
// build.gradle.kts
// androidTestImplementation("androidx.compose.ui:ui-test-junit4")
// debugImplementation("androidx.compose.ui:ui-tooling")

class ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Using Roborazzi or Paparazzi for screenshot testing
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5,
        theme = "android:Theme.Material3.Light"
    )

    @Test
    fun `note list default state`() {
        paparazzi.snapshot {
            NoteListScreen(
                notes = listOf(
                    Note("1", "Shopping"),
                    Note("2", "Meeting Notes")
                )
            )
        }
        // First run: saves golden image
        // Next runs: compares pixel-by-pixel
    }

    @Test
    fun `empty state screenshot`() {
        paparazzi.snapshot {
            NoteListScreen(notes = emptyList())
        }
    }

    @Test
    fun `dark theme screenshot`() {
        paparazzi.snapshot {
            MaterialTheme(
                colorScheme = darkColorScheme()
            ) {
                NoteListScreen(
                    notes = listOf(Note("1", "Dark Mode"))
                )
            }
        }
    }

    // Alternative: Compose screenshot API (newer)
    @Test
    fun `compose native screenshot`() {
        composeTestRule.setContent {
            NoteListScreen(notes = sampleNotes)
        }

        composeTestRule
            .onNodeWithTag("note_list")
            .captureToImage()
            .assertAgainstGolden(goldenName = "note_list")
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Screenshot Test", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Visual regression testing captures composable snapshots and compares against golden images.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("Test Code:", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            testCode,
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp,
            modifier = Modifier.horizontalScroll(rememberScrollState())
        )

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Libraries: Paparazzi (JVM, no emulator), Roborazzi (Robolectric-based), " +
                "or native captureToImage(). Run ./gradlew recordPaparazzi to update golden files.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
