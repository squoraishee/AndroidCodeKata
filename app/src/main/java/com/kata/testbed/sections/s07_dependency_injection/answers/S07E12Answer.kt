package com.kata.testbed.sections.s07_dependency_injection.answers

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

private interface EventHandler {
    val name: String
    fun handle(event: String): String
}

private class LoggingHandler : EventHandler {
    override val name = "LoggingHandler"
    override fun handle(event: String): String = "[$name] Logged: $event"
}

private class AnalyticsHandler : EventHandler {
    override val name = "AnalyticsHandler"
    override fun handle(event: String): String = "[$name] Tracked: $event"
}

private class NotificationHandler : EventHandler {
    override val name = "NotificationHandler"
    override fun handle(event: String): String = "[$name] Notified: $event"
}

private class EventDispatcher(private val handlers: Set<EventHandler>) {
    fun dispatch(event: String): List<String> =
        handlers.map { it.handle(event) }

    fun handlerCount(): Int = handlers.size
}

@Composable
fun S07E12Answer() {
    val handlers: Set<EventHandler> = setOf(
        LoggingHandler(),
        AnalyticsHandler(),
        NotificationHandler()
    )
    val dispatcher = EventDispatcher(handlers)
    val results = dispatcher.dispatch("user_signup")

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Multi-Bindings",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hilt collects implementations into a Set:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Module\n@InstallIn(SingletonComponent::class)\nabstract class HandlerModule {\n  @Binds @IntoSet\n  abstract fun bindLogging(\n    impl: LoggingHandler\n  ): EventHandler\n\n  @Binds @IntoSet\n  abstract fun bindAnalytics(\n    impl: AnalyticsHandler\n  ): EventHandler\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Dispatching 'user_signup' to ${dispatcher.handlerCount()} handlers:",
            fontWeight = FontWeight.SemiBold)
        results.forEach { Text(text = "  $it") }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "New handlers are added by binding @IntoSet. No dispatcher changes needed.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
