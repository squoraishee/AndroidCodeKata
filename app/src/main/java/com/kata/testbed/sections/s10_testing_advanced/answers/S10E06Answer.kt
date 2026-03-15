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
fun S10E06Answer() {
    val testCode = """
class NoteListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `displays note titles`() {
        val notes = listOf(
            Note("1", "Shopping List"),
            Note("2", "Meeting Notes")
        )

        composeTestRule.setContent {
            NoteListScreen(notes = notes)
        }

        composeTestRule
            .onNodeWithText("Shopping List")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Meeting Notes")
            .assertIsDisplayed()
    }

    @Test
    fun `empty state shows message`() {
        composeTestRule.setContent {
            NoteListScreen(notes = emptyList())
        }

        composeTestRule
            .onNodeWithText("No notes yet")
            .assertIsDisplayed()
    }

    @Test
    fun `find by test tag`() {
        composeTestRule.setContent {
            NoteListScreen(notes = listOf(Note("1", "Test")))
        }

        composeTestRule
            .onNodeWithTag("note_item_1")
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun `fab is visible`() {
        composeTestRule.setContent {
            NoteListScreen(notes = emptyList())
        }

        composeTestRule
            .onNodeWithContentDescription("Add Note")
            .assertIsDisplayed()
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Compose Test Basics", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("ComposeTestRule sets up a test composition. Find nodes and assert properties.")

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
            "Finders: onNodeWithText, onNodeWithTag, onNodeWithContentDescription. " +
                "Assertions: assertIsDisplayed, assertExists, assertHasClickAction.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
