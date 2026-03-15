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

private interface PaymentProcessor {
    fun process(amount: Double): String
}

private class StripeProcessor : PaymentProcessor {
    override fun process(amount: Double): String = "Stripe charged $${"%.2f".format(amount)}"
}

private class CheckoutService(private val processor: PaymentProcessor) {
    fun checkout(amount: Double): String = processor.process(amount)
}

@Composable
fun S07E07Answer() {
    val processor: PaymentProcessor = StripeProcessor()
    val service = CheckoutService(processor)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hilt @Binds vs @Provides",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "@Binds (interface to impl mapping):", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Module\n@InstallIn(SingletonComponent::class)\nabstract class PaymentModule {\n  @Binds\n  abstract fun bindProcessor(\n    impl: StripeProcessor\n  ): PaymentProcessor\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "@Provides (when you need creation logic):", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Module\n@InstallIn(SingletonComponent::class)\nobject PaymentModule {\n  @Provides\n  fun provideProcessor(): PaymentProcessor =\n    StripeProcessor()\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Use @Binds when: simple interface-to-impl mapping", style = MaterialTheme.typography.bodySmall)
        Text(text = "Use @Provides when: custom creation logic needed", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Result: ${service.checkout(49.99)}")
    }
}
