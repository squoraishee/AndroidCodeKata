package com.kata.testbed.sections.s08_room_persistence.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun S08E10Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Migration",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Schema v1 (before):", fontWeight = FontWeight.SemiBold)
        Text(
            text = "notes(id INTEGER, title TEXT, content TEXT)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Schema v2 (after):", fontWeight = FontWeight.SemiBold)
        Text(
            text = "notes(id INTEGER, title TEXT, content TEXT, is_pinned INTEGER DEFAULT 0)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.tertiary
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Migration object:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "val MIGRATION_1_2 = object : Migration(1, 2) {\n  override fun migrate(db: SupportSQLiteDatabase) {\n    db.execSQL(\n      \"ALTER TABLE notes \" +\n      \"ADD COLUMN is_pinned INTEGER \" +\n      \"NOT NULL DEFAULT 0\"\n    )\n  }\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Adding migration to builder:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "Room.databaseBuilder(...)\n  .addMigrations(MIGRATION_1_2)\n  .build()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Without migration, Room throws IllegalStateException on version change.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
