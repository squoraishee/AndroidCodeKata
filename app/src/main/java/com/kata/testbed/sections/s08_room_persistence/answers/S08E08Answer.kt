package com.kata.testbed.sections.s08_room_persistence.answers

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

private data class MNote(val noteId: Int, val title: String)
private data class Tag(val tagId: Int, val name: String)
private data class NoteTagCrossRef(val noteId: Int, val tagId: Int)
private data class NoteWithTags(val note: MNote, val tags: List<Tag>)

@Composable
fun S08E08Answer() {
    val notes = listOf(MNote(1, "Kotlin Tips"), MNote(2, "Room Guide"), MNote(3, "Compose Layout"))
    val tags = listOf(Tag(1, "android"), Tag(2, "kotlin"), Tag(3, "database"), Tag(4, "ui"))
    val crossRefs = listOf(
        NoteTagCrossRef(1, 1), NoteTagCrossRef(1, 2),
        NoteTagCrossRef(2, 1), NoteTagCrossRef(2, 2), NoteTagCrossRef(2, 3),
        NoteTagCrossRef(3, 1), NoteTagCrossRef(3, 4)
    )
    val tagMap = tags.associateBy { it.tagId }
    val notesWithTags = notes.map { note ->
        val noteTags = crossRefs.filter { it.noteId == note.noteId }
            .mapNotNull { tagMap[it.tagId] }
        NoteWithTags(note, noteTags)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Many-to-Many Relationship",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Junction table pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Entity(primaryKeys = [\"noteId\", \"tagId\"])\ndata class NoteTagCrossRef(\n  val noteId: Int,\n  val tagId: Int\n)\n\ndata class NoteWithTags(\n  @Embedded val note: Note,\n  @Relation(\n    parentColumn = \"noteId\",\n    entityColumn = \"tagId\",\n    associateBy = Junction(NoteTagCrossRef::class)\n  )\n  val tags: List<Tag>\n)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Notes with their tags:", fontWeight = FontWeight.SemiBold)
        notesWithTags.forEach { nwt ->
            val tagNames = nwt.tags.joinToString(", ") { "#${it.name}" }
            Text(text = "  ${nwt.note.title}: $tagNames")
        }
    }
}
