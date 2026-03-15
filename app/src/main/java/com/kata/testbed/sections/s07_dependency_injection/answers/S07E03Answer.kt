package com.kata.testbed.sections.s07_dependency_injection.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private interface AppLogger {
    fun log(msg: String): String
}

private class PrintLogger : AppLogger {
    override fun log(msg: String): String = "[LOG] $msg"
}

private interface TaskRepository {
    fun getTasks(): List<String>
}

private class InMemoryTaskRepository : TaskRepository {
    override fun getTasks(): List<String> = listOf("Buy groceries", "Write tests", "Deploy app")
}

private class TaskService(
    private val repository: TaskRepository,
    private val logger: AppLogger
) {
    fun listTasks(): List<String> {
        logger.log("Fetching tasks")
        return repository.getTasks()
    }

    fun logAndCount(): String {
        val tasks = listTasks()
        return logger.log("Found ${tasks.size} tasks")
    }
}

private class AppContainer {
    val logger: AppLogger = PrintLogger()
    val taskRepository: TaskRepository = InMemoryTaskRepository()
    val taskService: TaskService = TaskService(taskRepository, logger)
}

@Composable
fun S07E03Answer() {
    val container = AppContainer()
    val tasks = container.taskService.listTasks()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Manual DI Container",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "AppContainer wires the entire object graph:")
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "class AppContainer {\n  val logger = PrintLogger()\n  val repo = InMemoryTaskRepository()\n  val service = TaskService(repo, logger)\n}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Tasks from container:", fontWeight = FontWeight.SemiBold)
        tasks.forEach { task -> Text(text = "  - $task") }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = container.taskService.logAndCount())
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "All wiring happens in one place. No framework needed.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
