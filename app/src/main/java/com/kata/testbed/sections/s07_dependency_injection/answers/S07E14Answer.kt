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

private class AppScopedService(val name: String = "AppScoped") {
    fun describe(): String = "$name (lives for entire app)"
}

private class ActivityScopedService(val name: String = "ActivityScoped") {
    fun describe(): String = "$name (lives for one activity)"
}

private class FragmentScopedService(val name: String = "FragmentScoped") {
    fun describe(): String = "$name (lives for one fragment)"
}

@Composable
fun S07E14Answer() {
    val appService = AppScopedService()
    val activityService = ActivityScopedService()
    val fragmentService = FragmentScopedService()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Component Dependencies",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hilt Component Hierarchy:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "SingletonComponent (Application)\n  +-- ActivityRetainedComponent\n  |     +-- ActivityComponent\n  |     |     +-- FragmentComponent\n  |     |     +-- ViewComponent\n  |     +-- ViewModelComponent\n  +-- ServiceComponent",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Custom scope definition:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Scope\n@Retention(AnnotationRetention.RUNTIME)\nannotation class FeatureScope\n\n@DefineComponent(parent = SingletonComponent::class)\n@FeatureScope\ninterface FeatureComponent",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Scope lifetimes:", fontWeight = FontWeight.SemiBold)
        Text(text = "  ${appService.describe()}")
        Text(text = "  ${activityService.describe()}")
        Text(text = "  ${fragmentService.describe()}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Child components can access parent bindings, but not vice versa.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
