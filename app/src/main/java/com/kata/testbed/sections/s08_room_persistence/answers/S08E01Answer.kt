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

private data class NoteEntity(
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long
)

@Composable
fun S08E01Answer() {
    val sample = NoteEntity(
        id = 1,
        title = "First Note",
        content = "Hello Room!",
        createdAt = 1710500000000L
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Entity Definition",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Room @Entity annotation pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Entity(tableName = \"notes\")\ndata class NoteEntity(\n  @PrimaryKey(autoGenerate = true)\n  val id: Int = 0,\n  val title: String,\n  val content: String,\n  @ColumnInfo(name = \"created_at\")\n  val createdAt: Long\n)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Sample entity instance:", fontWeight = FontWeight.SemiBold)
        Text(text = "  id: ${sample.id}")
        Text(text = "  title: ${sample.title}")
        Text(text = "  content: ${sample.content}")
        Text(text = "  createdAt: ${sample.createdAt}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Room maps this data class to a SQLite table named 'notes'.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
