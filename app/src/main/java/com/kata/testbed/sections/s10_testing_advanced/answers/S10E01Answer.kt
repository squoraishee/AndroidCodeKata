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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private fun calculateDiscount(price: Double, percentage: Int): Double {
    require(percentage in 0..100) { "Percentage must be 0-100" }
    return price * (1 - percentage / 100.0)
}

@Composable
fun S10E01Answer() {
    val testCode = """
class CalculateDiscountTest {

    @Test
    fun `no discount returns original price`() {
        assertEquals(100.0, calculateDiscount(100.0, 0), 0.01)
    }

    @Test
    fun `50 percent discount halves price`() {
        assertEquals(50.0, calculateDiscount(100.0, 50), 0.01)
    }

    @Test
    fun `100 percent discount returns zero`() {
        assertEquals(0.0, calculateDiscount(100.0, 100), 0.01)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `negative percentage throws`() {
        calculateDiscount(100.0, -1)
    }

    @Test
    fun `result is not null`() {
        assertNotNull(calculateDiscount(50.0, 10))
    }

    @Test
    fun `discount is always less than or equal to price`() {
        assertTrue(calculateDiscount(100.0, 25) <= 100.0)
    }
}
    """.trimIndent()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("First Unit Test", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Function under test:", fontWeight = FontWeight.Bold)
        Text("calculateDiscount(100.0, 50) = ${calculateDiscount(100.0, 50)}", color = Color(0xFF2E7D32))
        Text("calculateDiscount(100.0, 0) = ${calculateDiscount(100.0, 0)}", color = Color(0xFF2E7D32))

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
            "Key assertions: assertEquals (exact match), assertTrue (boolean), " +
                "assertNotNull (non-null), expected= (exception).",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
