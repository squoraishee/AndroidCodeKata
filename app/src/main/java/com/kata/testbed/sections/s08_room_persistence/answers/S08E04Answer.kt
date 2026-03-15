package com.kata.testbed.sections.s08_room_persistence.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class Note(val id: Int, val title: String)

@Composable
fun S08E04Answer() {
    val notes = remember { mutableStateListOf(
        Note(1, "First note"),
        Note(2, "Second note")
    ) }
    var nextId = remember { 3 }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Flow Queries",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "DAO returns Flow for reactive queries:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Dao\ninterface NoteDao {\n  @Query(\"SELECT * FROM notes\")\n  fun getAllNotes(): Flow<List<Note>>\n\n  @Query(\"SELECT * FROM notes WHERE id = :id\")\n  fun getNoteById(id: Int): Flow<Note?>\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Collecting in ViewModel:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "val notes = noteDao.getAllNotes()\n  .stateIn(viewModelScope, \n    SharingStarted.WhileSubscribed(5000),\n    emptyList())",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Live simulation (${notes.size} notes):", fontWeight = FontWeight.SemiBold)
        notes.forEach { Text(text = "  [${it.id}] ${it.title}") }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            notes.add(Note(nextId++, "Note #${notes.size + 1}"))
        }) {
            Text("Add Note (simulates DB insert)")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Flow re-emits automatically when the underlying table changes.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
