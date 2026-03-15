package com.kata.testbed.sections.s09_clean_architecture.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class E12User(val id: String, val name: String)

data class E12Page<T>(
    val items: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val totalItems: Int
)

class E12UserRepository {
    private val allUsers = (1..23).map { E12User("$it", "User $it") }

    fun getPage(page: Int, pageSize: Int): E12Page<E12User> {
        val totalPages = (allUsers.size + pageSize - 1) / pageSize
        val safePage = page.coerceIn(1, totalPages)
        val start = (safePage - 1) * pageSize
        val end = (start + pageSize).coerceAtMost(allUsers.size)
        return E12Page(
            items = allUsers.subList(start, end),
            currentPage = safePage,
            totalPages = totalPages,
            totalItems = allUsers.size
        )
    }
}

class E12GetPagedUsersUseCase(private val repo: E12UserRepository) {
    operator fun invoke(page: Int, pageSize: Int = 5): E12Page<E12User> =
        repo.getPage(page, pageSize)
}

@Composable
fun S09E12Answer() {
    val getPagedUsers = remember { E12GetPagedUsersUseCase(E12UserRepository()) }
    var currentPage by remember { mutableIntStateOf(1) }
    val page = remember(currentPage) { getPagedUsers(currentPage) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Pagination Use Case", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Page ${page.currentPage} of ${page.totalPages} (${page.totalItems} total)")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        page.items.forEach { user ->
            Text("  ${user.id}. ${user.name}")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { if (currentPage > 1) currentPage-- },
                enabled = currentPage > 1
            ) {
                Text("Previous")
            }
            Button(
                onClick = { if (currentPage < page.totalPages) currentPage++ },
                enabled = currentPage < page.totalPages
            ) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Page<T> is a generic wrapper. The use case handles page bounds; UI just requests a page number.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
