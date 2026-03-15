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

private class CountedService(val instanceId: Int) {
    companion object {
        private var nextId = 0
        fun create(): CountedService = CountedService(++nextId)
        fun reset() { nextId = 0 }
    }
    override fun toString(): String = "CountedService#$instanceId"
}

private class SingletonScope {
    private val instance: CountedService by lazy { CountedService.create() }
    fun get(): CountedService = instance
}

private class UnscopedFactory {
    fun get(): CountedService = CountedService.create()
}

@Composable
fun S07E08Answer() {
    CountedService.reset()
    val singleton = SingletonScope()
    val unscoped = UnscopedFactory()

    val s1 = singleton.get()
    val s2 = singleton.get()
    val s3 = singleton.get()

    val u1 = unscoped.get()
    val u2 = unscoped.get()
    val u3 = unscoped.get()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Scoping: Singleton vs Unscoped",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "@Singleton (one shared instance):", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.tertiary)
        Text(text = "Request 1: $s1")
        Text(text = "Request 2: $s2")
        Text(text = "Request 3: $s3")
        Text(text = "Same instance? ${s1 === s2 && s2 === s3}",
            fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Unscoped (new instance each time):", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.error)
        Text(text = "Request 1: $u1")
        Text(text = "Request 2: $u2")
        Text(text = "Request 3: $u3")
        Text(text = "Same instance? ${u1 === u2}",
            fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "@Singleton: one instance per component. Unscoped: new each injection.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
