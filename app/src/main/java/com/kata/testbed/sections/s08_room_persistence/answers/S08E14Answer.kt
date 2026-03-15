package com.kata.testbed.sections.s08_room_persistence.answers

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

private data class Article(val id: Int, val title: String, val body: String)

private class FakeFtsSearch {
    private val articles = listOf(
        Article(1, "Getting Started with Kotlin", "Kotlin is a modern language for JVM development. It offers null safety and concise syntax."),
        Article(2, "Room Database Guide", "Room provides an abstraction layer over SQLite. Use annotations to define entities and DAOs."),
        Article(3, "Jetpack Compose Basics", "Compose is a declarative UI toolkit. Build UIs with composable functions."),
        Article(4, "Kotlin Coroutines Deep Dive", "Coroutines provide structured concurrency in Kotlin. Use suspend functions for async work."),
        Article(5, "Advanced Room Queries", "Room supports complex queries with JOIN, subqueries, and full-text search via FTS4.")
    )

    fun search(query: String): List<Article> =
        articles.filter { article ->
            article.title.contains(query, ignoreCase = true) ||
                article.body.contains(query, ignoreCase = true)
        }
}

@Composable
fun S08E14Answer() {
    val fts = FakeFtsSearch()
    val kotlinResults = fts.search("Kotlin")
    val roomResults = fts.search("Room")

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Full-Text Search (FTS4)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "FTS entity pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Fts4(contentEntity = Article::class)\n@Entity(tableName = \"articles_fts\")\ndata class ArticleFts(\n  val title: String,\n  val body: String\n)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "MATCH query in DAO:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Query(\n  \"SELECT * FROM articles \" +\n  \"JOIN articles_fts ON articles.rowid = articles_fts.rowid \" +\n  \"WHERE articles_fts MATCH :query\"\n)\nfun search(query: String): List<Article>",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Search 'Kotlin' (${kotlinResults.size} results):", fontWeight = FontWeight.SemiBold)
        kotlinResults.forEach { Text(text = "  - ${it.title}") }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Search 'Room' (${roomResults.size} results):", fontWeight = FontWeight.SemiBold)
        roomResults.forEach { Text(text = "  - ${it.title}") }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "FTS4 creates an inverted index for fast full-text search across large datasets.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
