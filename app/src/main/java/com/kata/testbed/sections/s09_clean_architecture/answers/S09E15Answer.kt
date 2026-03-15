package com.kata.testbed.sections.s09_clean_architecture.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Data Layer ---
private data class NoteEntity(val id: Long, val titleCol: String, val bodyCol: String, val timestampMs: Long)

private interface NoteDao {
    fun getAll(): List<NoteEntity>
    fun insert(entity: NoteEntity)
}

private class FakeNoteDao : NoteDao {
    private val notes = mutableListOf(
        NoteEntity(1, "Shopping", "Milk, eggs, bread", 1700000000000L),
        NoteEntity(2, "TODO", "Finish clean arch exercise", 1700100000000L)
    )
    override fun getAll(): List<NoteEntity> = notes.toList()
    override fun insert(entity: NoteEntity) { notes.add(entity) }
}

// --- Domain Layer ---
private data class Note(val id: String, val title: String, val body: String, val createdAt: String)

private interface NoteRepository {
    fun getAllNotes(): List<Note>
}

// --- Data Layer (impl) ---
private class RoomNoteRepository(private val dao: NoteDao) : NoteRepository {
    override fun getAllNotes(): List<Note> = dao.getAll().map { entity ->
        Note(
            id = entity.id.toString(),
            title = entity.titleCol,
            body = entity.bodyCol,
            createdAt = java.text.SimpleDateFormat("MMM dd", java.util.Locale.US)
                .format(java.util.Date(entity.timestampMs))
        )
    }
}

// --- Domain Layer (use case) ---
private class GetNotesUseCase(private val repo: NoteRepository) {
    operator fun invoke(): List<Note> = repo.getAllNotes()
}

// --- UI Layer ---
private sealed interface NotesUiState {
    data object Loading : NotesUiState
    data class Success(val notes: List<NoteUiModel>) : NotesUiState
}

private data class NoteUiModel(val title: String, val preview: String, val date: String)

private fun Note.toUiModel(): NoteUiModel = NoteUiModel(
    title = title,
    preview = if (body.length > 30) body.take(30) + "..." else body,
    date = createdAt
)

private class NotesViewModel(private val getNotes: GetNotesUseCase) {
    var state: NotesUiState = NotesUiState.Loading; private set
    fun load() {
        state = NotesUiState.Success(getNotes().map { it.toUiModel() })
    }
}

@Composable
private fun LayerLabel(name: String, color: Color, detail: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .background(color.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(name, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = color)
        Text(detail, fontSize = 11.sp)
    }
}

@Composable
fun S09E15Answer() {
    val dao = FakeNoteDao()
    val repo: NoteRepository = RoomNoteRepository(dao)
    val getNotes = GetNotesUseCase(repo)
    val viewModel = NotesViewModel(getNotes)
    viewModel.load()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Full Vertical Slice", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Data flows: Entity -> DAO -> Repository -> UseCase -> ViewModel -> UI")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Layer Stack:", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        LayerLabel("Data: Entity", Color(0xFF2E7D32), "NoteEntity(id, titleCol, bodyCol, timestampMs)")
        LayerLabel("Data: DAO", Color(0xFF2E7D32), "NoteDao.getAll() -> List<NoteEntity>")
        LayerLabel("Data: Repo Impl", Color(0xFF2E7D32), "RoomNoteRepository maps Entity -> Domain")
        LayerLabel("Domain: Model", Color(0xFF1565C0), "Note(id, title, body, createdAt)")
        LayerLabel("Domain: UseCase", Color(0xFF1565C0), "GetNotesUseCase invokes repository")
        LayerLabel("UI: ViewModel", Color(0xFF6A1B9A), "NotesViewModel exposes NotesUiState")
        LayerLabel("UI: Model", Color(0xFF6A1B9A), "NoteUiModel(title, preview, date)")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Live output:", fontWeight = FontWeight.Bold)
        when (val state = viewModel.state) {
            is NotesUiState.Loading -> Text("  Loading...")
            is NotesUiState.Success -> {
                state.notes.forEach { note ->
                    Text("  ${note.title} (${note.date})")
                    Text("    ${note.preview}")
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Each layer transforms data into its own shape. Changes in one layer don't ripple through the stack.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
