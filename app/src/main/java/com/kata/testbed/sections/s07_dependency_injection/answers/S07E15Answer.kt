package com.kata.testbed.sections.s07_dependency_injection.answers

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

private interface MessageSender {
    fun send(msg: String): String
}

private interface MessageFormatter {
    fun format(msg: String): String
}

private class PlainFormatter : MessageFormatter {
    override fun format(msg: String): String = msg.uppercase()
}

private class EmailSender(private val formatter: MessageFormatter) : MessageSender {
    override fun send(msg: String): String = "Email: ${formatter.format(msg)}"
}

private class NotificationCoordinator(private val sender: MessageSender) {
    fun notify(msg: String): String = sender.send(msg)
}

@Composable
fun S07E15Answer() {
    val formatter: MessageFormatter = PlainFormatter()
    val sender: MessageSender = EmailSender(formatter)
    val coordinator = NotificationCoordinator(sender)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "DI Architecture Review",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "BEFORE (tangled graph):", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.error)
        Text(
            text = "class ServiceA {\n  val b = ServiceB() // creates its own deps\n  val c = ServiceC(b) // shares b, tight coupling\n}\nclass ServiceB {\n  val a = ServiceA() // CIRCULAR!\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.error
        )
        Text(text = "Problems: circular dependency, hidden coupling, untestable",
            style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "AFTER (clean acyclic graph):", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.tertiary)
        Text(
            text = "interface MessageFormatter\ninterface MessageSender\n\nclass PlainFormatter : MessageFormatter\nclass EmailSender(\n  formatter: MessageFormatter\n) : MessageSender\nclass NotificationCoordinator(\n  sender: MessageSender\n)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = "Dependency flow: Coordinator -> Sender -> Formatter",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Result: ${coordinator.notify("Hello world")}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Break cycles by extracting interfaces and inverting dependencies.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
