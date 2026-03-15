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

private interface Storage {
    fun save(key: String, value: String): String
    fun load(key: String): String
}

private class LocalStorage : Storage {
    private val data = mutableMapOf<String, String>()
    override fun save(key: String, value: String): String {
        data[key] = value
        return "LocalStorage: saved $key=$value"
    }
    override fun load(key: String): String = "LocalStorage: ${data[key] ?: "not found"}"
}

private class CloudStorage : Storage {
    private val data = mutableMapOf<String, String>()
    override fun save(key: String, value: String): String {
        data[key] = value
        return "CloudStorage: uploaded $key=$value"
    }
    override fun load(key: String): String = "CloudStorage: ${data[key] ?: "not found"}"
}

private class SettingsManager(
    private val local: Storage,
    private val cloud: Storage
) {
    fun saveLocally(key: String, value: String): String = local.save(key, value)
    fun saveToCloud(key: String, value: String): String = cloud.save(key, value)
    fun loadLocal(key: String): String = local.load(key)
    fun loadCloud(key: String): String = cloud.load(key)
}

@Composable
fun S07E10Answer() {
    val manager = SettingsManager(
        local = LocalStorage(),
        cloud = CloudStorage()
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Qualifiers",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Qualifier annotations distinguish implementations:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Qualifier annotation class LocalStore\n@Qualifier annotation class CloudStore\n\nclass SettingsManager @Inject constructor(\n  @LocalStore private val local: Storage,\n  @CloudStore private val cloud: Storage\n)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = manager.saveLocally("theme", "dark"))
        Text(text = manager.saveToCloud("theme", "dark"))
        Text(text = manager.loadLocal("theme"))
        Text(text = manager.loadCloud("theme"))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Same interface, different impls selected by qualifier annotation.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
