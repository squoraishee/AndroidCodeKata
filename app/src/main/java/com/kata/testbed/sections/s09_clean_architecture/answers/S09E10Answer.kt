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

data class E10User(val id: String, val name: String, val email: String)

sealed class E10ValidationError {
    data object EmptyName : E10ValidationError()
    data object InvalidEmail : E10ValidationError()
    data class TooShort(val field: String, val minLength: Int) : E10ValidationError()
}

sealed class E10CreateUserResult {
    data class Success(val user: E10User) : E10CreateUserResult()
    data class ValidationFailed(val errors: List<E10ValidationError>) : E10CreateUserResult()
}

class E10CreateUserUseCase {
    operator fun invoke(name: String, email: String): E10CreateUserResult {
        val errors = mutableListOf<E10ValidationError>()

        if (name.isBlank()) errors.add(E10ValidationError.EmptyName)
        if (name.length < 2 && name.isNotBlank()) errors.add(E10ValidationError.TooShort("name", 2))
        if (!email.contains("@")) errors.add(E10ValidationError.InvalidEmail)

        if (errors.isNotEmpty()) return E10CreateUserResult.ValidationFailed(errors)

        val user = E10User(id = "generated-1", name = name.trim(), email = email.trim())
        return E10CreateUserResult.Success(user)
    }
}

fun E10ValidationError.displayMessage(): String = when (this) {
    is E10ValidationError.EmptyName -> "Name cannot be empty"
    is E10ValidationError.InvalidEmail -> "Email must contain '@'"
    is E10ValidationError.TooShort -> "$field must be at least $minLength characters"
}

@Composable
fun S09E10Answer() {
    val createUser = E10CreateUserUseCase()

    val validResult = createUser("Alice", "alice@example.com")
    val emptyNameResult = createUser("", "alice@example.com")
    val badEmailResult = createUser("A", "invalid-email")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Input Validation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Validation happens in the use case, before reaching the repository.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        listOf(
            "Valid input (\"Alice\", \"alice@example.com\")" to validResult,
            "Empty name (\"\", \"alice@example.com\")" to emptyNameResult,
            "Short name + bad email (\"A\", \"invalid\")" to badEmailResult
        ).forEach { (label, result) ->
            Text(label, fontWeight = FontWeight.Bold)
            when (result) {
                is E10CreateUserResult.Success -> {
                    Text("  Created: ${result.user.name} (${result.user.email})", color = Color(0xFF2E7D32))
                }
                is E10CreateUserResult.ValidationFailed -> {
                    result.errors.forEach { error ->
                        Text("  ${error.displayMessage()}", color = Color(0xFFC62828))
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Validation is business logic and belongs in the use case, not the UI or repository.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
