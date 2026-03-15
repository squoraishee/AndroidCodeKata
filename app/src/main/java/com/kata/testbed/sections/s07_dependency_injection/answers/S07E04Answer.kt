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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private object ServiceLocator {
    private val services = mutableMapOf<String, Any>()
    fun register(key: String, service: Any) { services[key] = service }
    fun get(key: String): Any = services[key] ?: error("Not found: $key")
}

private class BadOrderService {
    fun placeOrder(item: String): String {
        val db = ServiceLocator.get("database") as String
        return "Order '$item' saved to $db (pulled from locator)"
    }
}

private class GoodOrderService(private val database: String) {
    fun placeOrder(item: String): String {
        return "Order '$item' saved to $database (injected via constructor)"
    }
}

@Composable
fun S07E04Answer() {
    ServiceLocator.register("database", "PostgresDB")
    val badService = BadOrderService()
    val goodService = GoodOrderService("PostgresDB")

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Service Locator Refactor",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "BEFORE (Service Locator anti-pattern):", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.error)
        Text(
            text = "val db = ServiceLocator.get(\"database\")",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(text = badService.placeOrder("Widget"))
        Text(
            text = "Problem: Hidden dependency, hard to test, runtime failures",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "AFTER (Constructor Injection):", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.tertiary)
        Text(
            text = "class GoodOrderService(private val database: String)",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(text = goodService.placeOrder("Widget"))
        Text(
            text = "Benefit: Explicit deps, compile-time safety, easy testing",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
