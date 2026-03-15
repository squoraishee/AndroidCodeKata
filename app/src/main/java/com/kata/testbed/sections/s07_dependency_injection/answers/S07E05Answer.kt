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

private interface AnalyticsService {
    fun trackEvent(name: String): String
}

private class RealAnalyticsService : AnalyticsService {
    override fun trackEvent(name: String): String = "Tracked: $name"
}

private class MainViewModel(private val analytics: AnalyticsService) {
    fun onButtonClick(): String = analytics.trackEvent("button_clicked")
}

@Composable
fun S07E05Answer() {
    val analytics = RealAnalyticsService()
    val viewModel = MainViewModel(analytics)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hilt Setup Pattern",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Step 1: Application class", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@HiltAndroidApp\nclass MyApp : Application()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Step 2: Activity entry point", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@AndroidEntryPoint\nclass MainActivity : ComponentActivity()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Step 3: Injectable class", fontWeight = FontWeight.SemiBold)
        Text(
            text = "class RealAnalyticsService @Inject constructor() : AnalyticsService",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Simulated result:", fontWeight = FontWeight.SemiBold)
        Text(text = viewModel.onButtonClick())
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Hilt generates the wiring code at compile time.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
