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

@Composable
fun S08E03Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Database Setup",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Step 1: Define the Database class", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Database(\n  entities = [NoteEntity::class],\n  version = 1,\n  exportSchema = true\n)\nabstract class AppDatabase : RoomDatabase() {\n  abstract fun noteDao(): NoteDao\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Step 2: Build with databaseBuilder", fontWeight = FontWeight.SemiBold)
        Text(
            text = "val db = Room.databaseBuilder(\n  context.applicationContext,\n  AppDatabase::class.java,\n  \"app-database\"\n).build()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Step 3: Access DAOs", fontWeight = FontWeight.SemiBold)
        Text(
            text = "val noteDao = db.noteDao()\n// Use DAO methods to interact with the database",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Key points:", fontWeight = FontWeight.SemiBold)
        Text(text = "  - Database class must be abstract")
        Text(text = "  - List all entities in @Database annotation")
        Text(text = "  - Provide abstract DAO accessor methods")
        Text(text = "  - Use singleton pattern in production")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Room generates the implementation at compile time via annotation processing.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
