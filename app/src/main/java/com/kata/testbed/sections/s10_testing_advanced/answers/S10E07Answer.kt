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
fun S10E07Answer() {
    val testCode = """
class UserInteractionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `clicking add button opens editor`() {
        composeTestRule.setContent {
            NoteApp()
        }

        composeTestRule
            .onNodeWithContentDescription("Add Note")
            .performClick()

        composeTestRule
            .onNodeWithText("New Note")
            .assertIsDisplayed()
    }

    @Test
    fun `typing in title field updates state`() {
        composeTestRule.setContent {
            NoteEditorScreen()
        }

        composeTestRule
            .onNodeWithTag("title_field")
            .performTextInput("My New Note")

        composeTestRule
            .onNodeWithText("My New Note")
            .assertIsDisplayed()
    }

    @Test
    fun `clear button resets the field`() {
        composeTestRule.setContent {
            NoteEditorScreen()
        }

        composeTestRule
            .onNodeWithTag("title_field")
            .performTextInput("Some text")

        composeTestRule
            .onNodeWithTag("clear_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("title_field")
            .assertTextEquals("")
    }

    @Test
    fun `scroll to item in long list`() {
        composeTestRule.setContent {
            NoteListScreen(
                notes = (1..50).map { Note("${'$'}it", "Note ${'$'}it") }
            )
        }

        composeTestRule
            .onNodeWithText("Note 50")
            .performScrollTo()
            .assertIsDisplayed()
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("User Interaction Test", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Simulate clicks, text input, scrolling, and verify the resulting UI state.")

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
            "Actions: performClick(), performTextInput(text), performScrollTo(), " +
                "performTextClearance(). Chain with assertions.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
