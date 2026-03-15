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

// Simulated layer definitions with their "imports"
private data class LayerInfo(
    val name: String,
    val classes: List<String>,
    val imports: List<String>
)

private val domainLayer = LayerInfo(
    name = "Domain",
    classes = listOf("User", "UserRepository", "GetUsersUseCase"),
    imports = listOf("kotlin.collections.List", "kotlin.String")
)

private val dataLayer = LayerInfo(
    name = "Data",
    classes = listOf("UserEntity", "UserDao", "RoomUserRepository"),
    imports = listOf(
        "androidx.room.Entity",
        "androidx.room.Dao",
        "androidx.room.Query",
        "com.example.domain.User",
        "com.example.domain.UserRepository"
    )
)

private val uiLayer = LayerInfo(
    name = "UI",
    classes = listOf("UserListScreen", "UserViewModel"),
    imports = listOf(
        "androidx.compose.runtime.Composable",
        "androidx.lifecycle.ViewModel",
        "com.example.domain.GetUsersUseCase"
    )
)

private val androidPackages = listOf("androidx.", "android.", "com.google.android")

private fun checkDependencyRule(layer: LayerInfo): Pair<Boolean, List<String>> {
    val violations = layer.imports.filter { imp ->
        androidPackages.any { imp.startsWith(it) }
    }
    return (violations.isEmpty()) to violations
}

@Composable
fun S09E14Answer() {
    val layers = listOf(domainLayer, dataLayer, uiLayer)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Dependency Rule", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Domain layer must have zero Android/framework imports.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        layers.forEach { layer ->
            val (passes, violations) = checkDependencyRule(layer)
            val shouldPass = layer.name == "Domain"

            Text("${layer.name} Layer:", fontWeight = FontWeight.Bold)
            Text("  Classes: ${layer.classes.joinToString()}")
            Text("  Imports: ${layer.imports.size}")

            if (shouldPass) {
                if (passes) {
                    Text("  PASS: No Android imports", color = Color(0xFF2E7D32))
                } else {
                    Text("  FAIL: Found Android imports", color = Color(0xFFC62828))
                    violations.forEach { Text("    - $it", color = Color(0xFFC62828)) }
                }
            } else {
                Text("  Android imports (allowed in this layer):", color = Color(0xFF1565C0))
                violations.forEach { Text("    - $it") }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: The dependency rule states inner layers (domain) must not know about outer layers " +
                "(data, UI, framework). Only domain imports are Kotlin stdlib.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
