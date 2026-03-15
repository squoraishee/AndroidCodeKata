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

private interface Logger {
    fun log(message: String): String
}

private class ConsoleLogger : Logger {
    override fun log(message: String): String = "Console: $message"
}

private class FileLogger : Logger {
    override fun log(message: String): String = "File: $message"
}

private class NotificationService(private val logger: Logger) {
    fun sendNotification(to: String): String {
        val logEntry = logger.log("Notification sent to $to")
        return "Sent to $to | Logged as: $logEntry"
    }
}

@Composable
fun S07E02Answer() {
    val consoleService = NotificationService(ConsoleLogger())
    val fileService = NotificationService(FileLogger())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Interface Abstraction",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Same service, different logger implementations:")
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "interface Logger { fun log(message: String): String }",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "With ConsoleLogger:", fontWeight = FontWeight.SemiBold)
        Text(text = consoleService.sendNotification("Alice"))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "With FileLogger:", fontWeight = FontWeight.SemiBold)
        Text(text = fileService.sendNotification("Alice"))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "The service depends on the Logger interface, not a concrete class.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
