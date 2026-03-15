package com.kata.testbed.sections.s09_clean_architecture.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class Article(val id: String, val title: String)

private enum class DataSource { CACHE, NETWORK }

private data class DataWithSource<T>(
    val data: T,
    val source: DataSource,
    val isStale: Boolean = false
)

private interface ArticleCache {
    fun get(): List<Article>?
    fun put(articles: List<Article>)
}

private interface ArticleNetwork {
    fun fetch(): List<Article>
}

private class InMemoryCache : ArticleCache {
    private var cached: List<Article>? = null
    override fun get(): List<Article>? = cached
    override fun put(articles: List<Article>) { cached = articles }
}

private class SimulatedNetwork(private val shouldFail: Boolean = false) : ArticleNetwork {
    override fun fetch(): List<Article> {
        if (shouldFail) throw RuntimeException("Network unavailable")
        return listOf(
            Article("1", "Fresh Article A"),
            Article("2", "Fresh Article B")
        )
    }
}

private class GetArticlesOfflineFirstUseCase(
    private val cache: ArticleCache,
    private val network: ArticleNetwork
) {
    operator fun invoke(): DataWithSource<List<Article>> {
        val cached = cache.get()

        return try {
            val fresh = network.fetch()
            cache.put(fresh)
            DataWithSource(fresh, DataSource.NETWORK)
        } catch (_: Exception) {
            if (cached != null) {
                DataWithSource(cached, DataSource.CACHE, isStale = true)
            } else {
                DataWithSource(emptyList(), DataSource.CACHE, isStale = true)
            }
        }
    }
}

@Composable
fun S09E11Answer() {
    // Scenario 1: Network available
    val cache1 = InMemoryCache()
    cache1.put(listOf(Article("1", "Cached Article")))
    val networkOk = GetArticlesOfflineFirstUseCase(cache1, SimulatedNetwork(shouldFail = false))
    val result1 = networkOk()

    // Scenario 2: Network down, cache available
    val cache2 = InMemoryCache()
    cache2.put(listOf(Article("1", "Stale Cached Article")))
    val networkDown = GetArticlesOfflineFirstUseCase(cache2, SimulatedNetwork(shouldFail = true))
    val result2 = networkDown()

    // Scenario 3: Network down, no cache
    val cache3 = InMemoryCache()
    val nothingAvailable = GetArticlesOfflineFirstUseCase(cache3, SimulatedNetwork(shouldFail = true))
    val result3 = nothingAvailable()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Offline-First", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Read cache first, try network refresh, fall back to stale cache.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        listOf(
            "Network OK" to result1,
            "Network down (cached)" to result2,
            "Network down (no cache)" to result3
        ).forEach { (label, result) ->
            Text("$label:", fontWeight = FontWeight.Bold)
            val sourceColor = if (result.source == DataSource.NETWORK) Color(0xFF2E7D32) else Color(0xFFE65100)
            Text("  Source: ${result.source}", color = sourceColor)
            Text("  Stale: ${result.isStale}")
            result.data.forEach { Text("  - ${it.title}") }
            Spacer(modifier = Modifier.height(6.dp))
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Use case orchestrates cache and network. UI gets data with metadata about freshness.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
