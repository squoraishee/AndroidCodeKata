package com.kata.testbed.sections.s10_testing_advanced.answers

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun S10E04Answer() {
    val testCode = """
// Add to build.gradle.kts:
// testImplementation("app.cash.turbine:turbine:1.0.0")

class FlowTestWithTurbine {

    @Test
    fun `search flow emits filtered results`() = runTest {
        val repo = FakeNoteRepository()
        repo.notes.addAll(listOf(
            Note("1", "Kotlin basics"),
            Note("2", "Compose UI"),
            Note("3", "Kotlin coroutines")
        ))

        val searchFlow = repo.searchNotes("Kotlin")

        searchFlow.test {
            val results = awaitItem()
            assertEquals(2, results.size)
            assertTrue(results.all { "Kotlin" in it.title })
            awaitComplete()
        }
    }

    @Test
    fun `state flow emits loading then success`() = runTest {
        val viewModel = NoteViewModel(FakeNoteRepository())

        viewModel.state.test {
            // First emission: Loading
            assertEquals(UiState.Loading, awaitItem())

            // Trigger load
            viewModel.loadNotes()

            // Second emission: Success
            val success = awaitItem()
            assertTrue(success is UiState.Success)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `flow with errors`() = runTest {
        val errorFlow = flow<String> {
            emit("first")
            throw IOException("network down")
        }

        errorFlow.test {
            assertEquals("first", awaitItem())
            val error = awaitError()
            assertTrue(error is IOException)
        }
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Testing Flows", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Turbine makes Flow testing declarative: await each emission and assert.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("Test Code:", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            testCode,
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp,
            modifier = Modifier.horizontalScroll(rememberScrollState())
        )

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Turbine API: awaitItem() (next emission), awaitComplete() (flow ended), " +
                "awaitError() (flow threw), cancelAndIgnoreRemainingEvents().",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
