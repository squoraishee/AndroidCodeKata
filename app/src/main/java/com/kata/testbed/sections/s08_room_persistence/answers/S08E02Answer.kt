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

private data class Task(val id: Int, val name: String, val done: Boolean)

private class FakeTaskDao {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    fun insert(name: String): Task {
        val task = Task(id = nextId++, name = name, done = false)
        tasks.add(task)
        return task
    }

    fun getAll(): List<Task> = tasks.toList()

    fun delete(task: Task) {
        tasks.removeAll { it.id == task.id }
    }
}

@Composable
fun S08E02Answer() {
    val dao = FakeTaskDao()
    dao.insert("Buy groceries")
    dao.insert("Write tests")
    val toDelete = dao.insert("Temp task")
    dao.insert("Deploy app")
    val beforeDelete = dao.getAll()
    dao.delete(toDelete)
    val afterDelete = dao.getAll()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Basic DAO",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "DAO interface pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Dao\ninterface TaskDao {\n  @Insert\n  suspend fun insert(task: Task)\n\n  @Query(\"SELECT * FROM tasks\")\n  suspend fun getAll(): List<Task>\n\n  @Delete\n  suspend fun delete(task: Task)\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Before delete (${beforeDelete.size} items):", fontWeight = FontWeight.SemiBold)
        beforeDelete.forEach { Text(text = "  [${it.id}] ${it.name}") }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "After deleting '${toDelete.name}':", fontWeight = FontWeight.SemiBold)
        afterDelete.forEach { Text(text = "  [${it.id}] ${it.name}") }
    }
}
