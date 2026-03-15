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
fun S10E08Answer() {
    val testCode = """
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(
                LocalContext.current
            ).apply {
                navigatorProvider.addNavigator(
                    ComposeNavigator()
                )
            }
            NoteNavHost(navController = navController)
        }
    }

    @Test
    fun `start destination is note list`() {
        assertEquals(
            "note_list",
            navController.currentBackStackEntry
                ?.destination?.route
        )
    }

    @Test
    fun `clicking note navigates to detail`() {
        composeTestRule
            .onNodeWithText("Shopping List")
            .performClick()

        assertEquals(
            "note_detail/1",
            navController.currentBackStackEntry
                ?.destination?.route
        )
    }

    @Test
    fun `back from detail returns to list`() {
        // Navigate to detail
        composeTestRule
            .onNodeWithText("Shopping List")
            .performClick()

        // Press back
        composeTestRule
            .onNodeWithContentDescription("Back")
            .performClick()

        assertEquals(
            "note_list",
            navController.currentBackStackEntry
                ?.destination?.route
        )
    }

    @Test
    fun `back stack depth after navigation`() {
        composeTestRule
            .onNodeWithText("Shopping List")
            .performClick()

        assertEquals(
            2,
            navController.backStack.size
        )
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Navigation Test", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Use TestNavHostController to verify navigation routes and back stack behavior.")

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
            "Key: TestNavHostController exposes currentBackStackEntry and backStack for assertions. " +
                "Verify routes, not implementation details.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
