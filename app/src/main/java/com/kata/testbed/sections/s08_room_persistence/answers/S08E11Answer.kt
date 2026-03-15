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
fun S08E11Answer() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Auto Migration",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Simple auto-migration (add column):", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Database(\n  entities = [NoteEntity::class],\n  version = 2,\n  autoMigrations = [\n    AutoMigration(from = 1, to = 2)\n  ]\n)\nabstract class AppDatabase : RoomDatabase()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "When columns are renamed or deleted:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@RenameColumn(\n  tableName = \"notes\",\n  fromColumnName = \"body\",\n  toColumnName = \"content\"\n)\nclass Migration1To2Spec : AutoMigrationSpec\n\n@Database(\n  autoMigrations = [\n    AutoMigration(\n      from = 1, to = 2,\n      spec = Migration1To2Spec::class\n    )\n  ]\n)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "When to use each:", fontWeight = FontWeight.SemiBold)
        Text(text = "  Auto: add/remove/rename columns, add/remove tables")
        Text(text = "  Manual: data transformation, complex SQL needed")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Auto-migration requires exportSchema = true.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
