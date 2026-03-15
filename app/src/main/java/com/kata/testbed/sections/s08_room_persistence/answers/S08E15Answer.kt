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

private data class TestNote(val id: Int, val title: String)

private class InMemoryNoteDao {
    private val notes = mutableListOf<TestNote>()
    private var nextId = 1

    fun insert(title: String): TestNote {
        val note = TestNote(nextId++, title)
        notes.add(note)
        return note
    }

    fun getAll(): List<TestNote> = notes.toList()
    fun getById(id: Int): TestNote? = notes.find { it.id == id }
    fun delete(note: TestNote) { notes.removeAll { it.id == note.id } }
    fun clear() { notes.clear(); nextId = 1 }
}

private data class TestResult(val name: String, val passed: Boolean, val detail: String)

private fun runTests(): List<TestResult> {
    val results = mutableListOf<TestResult>()
    val dao = InMemoryNoteDao()

    dao.clear()
    val inserted = dao.insert("Test Note")
    val found = dao.getById(inserted.id)
    results.add(TestResult(
        "insert_note_canRetrieveById",
        found != null && found.title == "Test Note",
        "Inserted id=${inserted.id}, found=${found?.title}"
    ))

    dao.clear()
    dao.insert("A"); dao.insert("B"); dao.insert("C")
    results.add(TestResult(
        "insertMultiple_getAll_returnsAll",
        dao.getAll().size == 3,
        "Expected 3, got ${dao.getAll().size}"
    ))

    dao.clear()
    val toDelete = dao.insert("Delete me")
    dao.insert("Keep me")
    dao.delete(toDelete)
    val remaining = dao.getAll()
    results.add(TestResult(
        "delete_note_removedFromDb",
        remaining.size == 1 && remaining[0].title == "Keep me",
        "Remaining: ${remaining.map { it.title }}"
    ))

    dao.clear()
    val notFound = dao.getById(999)
    results.add(TestResult(
        "getById_nonExistent_returnsNull",
        notFound == null,
        "Expected null, got $notFound"
    ))

    return results
}

@Composable
fun S08E15Answer() {
    val testResults = runTests()
    val passed = testResults.count { it.passed }
    val total = testResults.size

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Database Testing",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Test setup pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@RunWith(AndroidJUnit4::class)\nclass NoteDaoTest {\n  private lateinit var db: AppDatabase\n  private lateinit var dao: NoteDao\n\n  @Before\n  fun createDb() {\n    db = Room.inMemoryDatabaseBuilder(\n      ApplicationProvider.getApplicationContext(),\n      AppDatabase::class.java\n    ).build()\n    dao = db.noteDao()\n  }\n\n  @After\n  fun closeDb() = db.close()\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Test results ($passed/$total passed):", fontWeight = FontWeight.SemiBold)
        testResults.forEach { result ->
            val icon = if (result.passed) "PASS" else "FAIL"
            val color = if (result.passed) MaterialTheme.colorScheme.tertiary
                else MaterialTheme.colorScheme.error
            Text(
                text = "  $icon ${result.name}",
                color = color,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "       ${result.detail}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
