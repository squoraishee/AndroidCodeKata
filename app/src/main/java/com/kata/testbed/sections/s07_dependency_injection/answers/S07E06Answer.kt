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

private class ApiClient(val baseUrl: String) {
    fun fetch(endpoint: String): String = "GET $baseUrl/$endpoint -> 200 OK"
}

private class ProductRepository(private val apiClient: ApiClient) {
    fun getProducts(): List<String> = listOf(
        apiClient.fetch("products/1"),
        apiClient.fetch("products/2")
    )
}

private class NetworkModule {
    fun provideApiClient(): ApiClient = ApiClient("https://api.example.com")
}

private class RepositoryModule(private val networkModule: NetworkModule) {
    fun provideProductRepository(): ProductRepository =
        ProductRepository(networkModule.provideApiClient())
}

@Composable
fun S07E06Answer() {
    val networkModule = NetworkModule()
    val repoModule = RepositoryModule(networkModule)
    val repository = repoModule.provideProductRepository()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hilt Modules (@Provides)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "NetworkModule:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Module\n@InstallIn(SingletonComponent::class)\nobject NetworkModule {\n  @Provides @Singleton\n  fun provideApiClient(): ApiClient =\n    ApiClient(\"https://api.example.com\")\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "RepositoryModule:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Module\n@InstallIn(SingletonComponent::class)\nobject RepositoryModule {\n  @Provides\n  fun provideProductRepo(\n    apiClient: ApiClient\n  ): ProductRepository =\n    ProductRepository(apiClient)\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Simulated output:", fontWeight = FontWeight.SemiBold)
        repository.getProducts().forEach { Text(text = it) }
    }
}
