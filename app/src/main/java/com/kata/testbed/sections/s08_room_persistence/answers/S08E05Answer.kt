package com.kata.testbed.sections.s08_room_persistence.answers

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

private data class Product(val id: Int, val name: String, val price: Double)

private class FakeProductDao {
    private val products = mutableMapOf<Int, Product>()

    fun insert(product: Product): String {
        if (products.containsKey(product.id)) return "IGNORED (already exists)"
        products[product.id] = product
        return "INSERTED"
    }

    fun upsert(product: Product): String {
        val existed = products.containsKey(product.id)
        products[product.id] = product
        return if (existed) "UPDATED" else "INSERTED"
    }

    fun getAll(): List<Product> = products.values.toList()
}

@Composable
fun S08E05Answer() {
    val dao = FakeProductDao()
    val r1 = dao.insert(Product(1, "Widget", 9.99))
    val r2 = dao.insert(Product(1, "Widget v2", 12.99))
    val r3 = dao.upsert(Product(2, "Gadget", 19.99))
    val r4 = dao.upsert(Product(2, "Gadget Pro", 24.99))

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Update and Upsert",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Annotation patterns:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Insert(onConflict = OnConflictStrategy.IGNORE)\nsuspend fun insert(product: Product)\n\n@Upsert\nsuspend fun upsert(product: Product)\n\n@Update\nsuspend fun update(product: Product)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "@Insert with IGNORE strategy:", fontWeight = FontWeight.SemiBold)
        Text(text = "  Insert Widget (id=1): $r1")
        Text(text = "  Insert Widget v2 (id=1): $r2")

        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "@Upsert (insert or update):", fontWeight = FontWeight.SemiBold)
        Text(text = "  Upsert Gadget (id=2): $r3")
        Text(text = "  Upsert Gadget Pro (id=2): $r4")

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Final state:", fontWeight = FontWeight.SemiBold)
        dao.getAll().forEach { Text(text = "  [${it.id}] ${it.name} - $${it.price}") }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Strategies: REPLACE, IGNORE, ABORT. @Upsert auto-detects insert vs update.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
