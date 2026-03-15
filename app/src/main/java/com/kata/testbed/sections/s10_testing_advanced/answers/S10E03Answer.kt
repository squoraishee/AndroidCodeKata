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
fun S10E03Answer() {
    val testCode = """
class CoroutineExampleTest {

    // Use StandardTestDispatcher for manual time control
    @Test
    fun `delayed operation completes after advancing`() = runTest {
        var result = ""

        launch {
            delay(1000)
            result = "done"
        }

        // Time hasn't advanced yet
        assertEquals("", result)

        // Advance virtual time by 1 second
        advanceTimeBy(1000)
        runCurrent()

        assertEquals("done", result)
    }

    // Test suspend functions directly
    @Test
    fun `repository returns data`() = runTest {
        val repo = FakeNoteRepository()
        repo.notes.add(Note("1", "Test"))

        // Call suspend function directly inside runTest
        val notes = repo.getAll()

        assertEquals(1, notes.size)
        assertEquals("Test", notes.first().title)
    }

    // advanceUntilIdle runs all pending coroutines
    @Test
    fun `viewModel loads after init`() = runTest {
        val viewModel = NoteViewModel(
            FakeNoteRepository(),
            dispatcher = StandardTestDispatcher(testScheduler)
        )

        // Run all pending coroutines
        advanceUntilIdle()

        assertTrue(viewModel.state.value is UiState.Success)
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Testing Coroutines", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("runTest provides virtual time control. No real waiting in tests.")

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
            "Key functions: runTest { } (test scope), advanceTimeBy() (virtual time), " +
                "advanceUntilIdle() (run all pending), StandardTestDispatcher (manual control).",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
