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

private interface WeatherRepository {
    fun getTemperature(city: String): String
}

private class RealWeatherRepository : WeatherRepository {
    override fun getTemperature(city: String): String =
        "Real API call for $city -> 72F (network delay)"
}

private class FakeWeatherRepository : WeatherRepository {
    private val data = mapOf("NYC" to "70F", "LA" to "85F")
    override fun getTemperature(city: String): String =
        "Fake: ${data[city] ?: "unknown"} (instant, no network)"
}

private class WeatherViewModel(private val repository: WeatherRepository) {
    fun getWeather(city: String): String = repository.getTemperature(city)
}

@Composable
fun S07E13Answer() {
    val realVm = WeatherViewModel(RealWeatherRepository())
    val fakeVm = WeatherViewModel(FakeWeatherRepository())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Testing with Hilt (@TestInstallIn)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Replace real module in tests:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Module\n@TestInstallIn(\n  components = [SingletonComponent::class],\n  replaces = [WeatherModule::class]\n)\nobject FakeWeatherModule {\n  @Provides\n  fun provideRepo(): WeatherRepository =\n    FakeWeatherRepository()\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Production (real repo):", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.tertiary)
        Text(text = "  NYC: ${realVm.getWeather("NYC")}")

        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Test (fake repo):", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.error)
        Text(text = "  NYC: ${fakeVm.getWeather("NYC")}")
        Text(text = "  LA: ${fakeVm.getWeather("LA")}")

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "@TestInstallIn swaps the real module for a fake at test compile time.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
