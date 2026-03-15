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

private data class Order(val id: Int, val customer: String)
private data class OrderItem(val id: Int, val orderId: Int, val product: String)

private class FakeOrderDao {
    private val orders = mutableListOf<Order>()
    private val items = mutableListOf<OrderItem>()
    private var nextOrderId = 1
    private var nextItemId = 1

    fun insertOrderWithItems(customer: String, products: List<String>): Pair<Boolean, String> {
        val orderId = nextOrderId++
        orders.add(Order(orderId, customer))
        products.forEach { product ->
            items.add(OrderItem(nextItemId++, orderId, product))
        }
        return true to "Order #$orderId with ${products.size} items committed"
    }

    fun insertOrderWithFailure(customer: String, products: List<String>): Pair<Boolean, String> {
        val orderId = nextOrderId++
        orders.add(Order(orderId, customer))
        return false to "Order #$orderId rolled back (item insert failed)"
    }

    fun getOrders(): List<Order> = orders.toList()
    fun getItems(): List<OrderItem> = items.toList()
}

@Composable
fun S08E12Answer() {
    val dao = FakeOrderDao()
    val (ok1, msg1) = dao.insertOrderWithItems("Alice", listOf("Laptop", "Mouse", "Keyboard"))
    val (ok2, msg2) = dao.insertOrderWithFailure("Bob", listOf("Phone"))

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Transactions",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "@Transaction DAO pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Dao\ninterface OrderDao {\n  @Insert\n  suspend fun insertOrder(order: Order): Long\n\n  @Insert\n  suspend fun insertItems(items: List<OrderItem>)\n\n  @Transaction\n  suspend fun insertOrderWithItems(\n    order: Order,\n    items: List<OrderItem>\n  ) {\n    val orderId = insertOrder(order)\n    insertItems(items.map {\n      it.copy(orderId = orderId.toInt())\n    })\n  }\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Success:", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.tertiary)
        Text(text = "  $msg1")

        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Failure:", fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.error)
        Text(text = "  $msg2")

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "DB state: ${dao.getOrders().size} orders, ${dao.getItems().size} items",
            fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "@Transaction ensures all operations succeed or all are rolled back.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
