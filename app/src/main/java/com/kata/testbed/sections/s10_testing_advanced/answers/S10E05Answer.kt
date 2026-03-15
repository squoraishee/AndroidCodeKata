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
fun S10E05Answer() {
    val testCode = """
class NoteViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeRepo: FakeNoteRepository
    private lateinit var viewModel: NoteViewModel

    @Before
    fun setUp() {
        fakeRepo = FakeNoteRepository()
        viewModel = NoteViewModel(fakeRepo)
    }

    @Test
    fun `initial state is Loading`() {
        assertEquals(UiState.Loading, viewModel.state.value)
    }

    @Test
    fun `loadNotes transitions Loading to Success`() = runTest {
        fakeRepo.notes.add(Note("1", "Test Note"))

        viewModel.state.test {
            assertEquals(UiState.Loading, awaitItem())

            viewModel.loadNotes()

            val success = awaitItem()
            assertTrue(success is UiState.Success)
            assertEquals(1, (success as UiState.Success).notes.size)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `loadNotes with error transitions to Error`() = runTest {
        fakeRepo.shouldThrow = true

        viewModel.state.test {
            assertEquals(UiState.Loading, awaitItem())

            viewModel.loadNotes()

            val error = awaitItem()
            assertTrue(error is UiState.Error)
            assertTrue("Failed" in (error as UiState.Error).message)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `delete updates the list`() = runTest {
        fakeRepo.notes.addAll(listOf(
            Note("1", "Keep"), Note("2", "Remove")
        ))
        viewModel.loadNotes()
        advanceUntilIdle()

        viewModel.deleteNote("2")
        advanceUntilIdle()

        val state = viewModel.state.value as UiState.Success
        assertEquals(1, state.notes.size)
        assertEquals("Keep", state.notes.first().title)
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("ViewModel State Test", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Assert the sequence of UI state transitions: Loading -> Success or Error.")

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
            "Key: MainDispatcherRule replaces Dispatchers.Main for tests. " +
                "Turbine verifies exact state sequence. FakeRepo.shouldThrow simulates errors.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
