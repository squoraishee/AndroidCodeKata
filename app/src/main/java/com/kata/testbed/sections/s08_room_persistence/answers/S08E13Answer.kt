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
fun S08E13Answer() {
    val preloadedData = listOf(
        "United States", "Canada", "Mexico",
        "United Kingdom", "France", "Germany"
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Prepopulate Database",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Option 1: createFromAsset (bundled .db file)", fontWeight = FontWeight.SemiBold)
        Text(
            text = "Room.databaseBuilder(\n  context, AppDatabase::class.java,\n  \"app.db\"\n).createFromAsset(\"databases/prepopulated.db\")\n.build()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Option 2: createFromFile (external storage)", fontWeight = FontWeight.SemiBold)
        Text(
            text = "Room.databaseBuilder(...)\n  .createFromFile(File(\"path/to/db\"))\n  .build()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Option 3: Callback seed on first create", fontWeight = FontWeight.SemiBold)
        Text(
            text = "Room.databaseBuilder(...)\n  .addCallback(object : RoomDatabase.Callback() {\n    override fun onCreate(db: SupportSQLiteDatabase) {\n      super.onCreate(db)\n      // Insert seed data\n    }\n  })\n  .build()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Simulated preloaded data:", fontWeight = FontWeight.SemiBold)
        preloadedData.forEach { Text(text = "  - $it") }
    }
}
