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
fun S10E02Answer() {
    val testCode = """
// Domain interface
interface NoteRepository {
    suspend fun getAll(): List<Note>
    suspend fun save(note: Note)
    suspend fun delete(id: String)
}

// Fake for testing - NO mocking library needed
class FakeNoteRepository : NoteRepository {
    val notes = mutableListOf<Note>()
    var saveCalled = 0; private set

    override suspend fun getAll() = notes.toList()

    override suspend fun save(note: Note) {
        saveCalled++
        notes.removeAll { it.id == note.id }
        notes.add(note)
    }

    override suspend fun delete(id: String) {
        notes.removeAll { it.id == id }
    }
}

// ViewModel test using the fake
class NoteViewModelTest {

    private val fakeRepo = FakeNoteRepository()
    private val viewModel = NoteViewModel(fakeRepo)

    @Test
    fun `load notes shows all notes`() = runTest {
        fakeRepo.notes.addAll(listOf(
            Note("1", "First"),
            Note("2", "Second")
        ))

        viewModel.loadNotes()

        val state = viewModel.state.value
        assertTrue(state is UiState.Success)
        assertEquals(2, (state as UiState.Success).notes.size)
    }

    @Test
    fun `save note calls repository`() = runTest {
        viewModel.saveNote(Note("1", "Test"))

        assertEquals(1, fakeRepo.saveCalled)
        assertEquals(1, fakeRepo.notes.size)
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Testing with Fakes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Fakes implement real interfaces with in-memory behavior. Preferred over mocking libraries.")

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
            "Key: Fakes are real implementations you control. They expose state (notes, saveCalled) " +
                "for verification without complex mock setup.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
