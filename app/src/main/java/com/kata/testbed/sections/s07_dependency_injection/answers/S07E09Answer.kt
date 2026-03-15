package com.kata.testbed.sections.s07_dependency_injection.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private interface NoteRepository {
    fun getNotes(): List<String>
    fun addNote(note: String)
}

private class FakeNoteRepository : NoteRepository {
    private val notes = mutableListOf("Buy milk", "Write code", "Read book")
    override fun getNotes(): List<String> = notes.toList()
    override fun addNote(note: String) { notes.add(note) }
}

private data class NoteUiState(
    val notes: List<String> = emptyList(),
    val count: Int = 0
)

private class NoteViewModel(private val repository: NoteRepository) {
    fun getState(): NoteUiState {
        val notes = repository.getNotes()
        return NoteUiState(notes = notes, count = notes.size)
    }

    fun addNote(note: String) {
        repository.addNote(note)
    }
}

@Composable
fun S07E09Answer() {
    val repository: NoteRepository = FakeNoteRepository()
    val viewModel = NoteViewModel(repository)
    viewModel.addNote("New task")
    val state = viewModel.getState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "ViewModel Injection",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hilt ViewModel pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@HiltViewModel\nclass NoteViewModel @Inject constructor(\n  private val repository: NoteRepository\n) : ViewModel()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "ViewModel state (${state.count} notes):", fontWeight = FontWeight.SemiBold)
        state.notes.forEachIndexed { index, note ->
            Text(text = "  ${index + 1}. $note")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "ViewModel receives its repository via @Inject constructor. Hilt creates it automatically.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
