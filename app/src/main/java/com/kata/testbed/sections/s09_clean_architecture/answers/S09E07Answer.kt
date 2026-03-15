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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class E07UserEntity(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val createdAtMillis: Long
)

data class E07User(
    val id: String,
    val fullName: String,
    val email: String,
    val createdAt: String
)

data class E07UserUiModel(
    val displayName: String,
    val emailLabel: String,
    val memberSince: String
)

fun E07UserEntity.toDomain(): E07User = E07User(
    id = id.toString(),
    fullName = "$firstName $lastName",
    email = emailAddress,
    createdAt = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
        .format(java.util.Date(createdAtMillis))
)

fun E07User.toUiModel(): E07UserUiModel = E07UserUiModel(
    displayName = fullName.uppercase(),
    emailLabel = "Email: $email",
    memberSince = "Member since $createdAt"
)

@Composable
fun S09E07Answer() {
    val entity = E07UserEntity(
        id = 1L,
        firstName = "Alice",
        lastName = "Smith",
        emailAddress = "alice@example.com",
        createdAtMillis = 1700000000000L
    )
    val domain = entity.toDomain()
    val uiModel = domain.toUiModel()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Mapper Pattern", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Each layer has its own model. Mappers convert between them.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("Entity (data layer):", fontWeight = FontWeight.Bold)
        Text("  firstName=${entity.firstName}, lastName=${entity.lastName}")
        Text("  emailAddress=${entity.emailAddress}")
        Text("  createdAtMillis=${entity.createdAtMillis}")

        Spacer(modifier = Modifier.height(8.dp))
        Text("Domain (business layer):", fontWeight = FontWeight.Bold)
        Text("  fullName=${domain.fullName}")
        Text("  email=${domain.email}")
        Text("  createdAt=${domain.createdAt}")

        Spacer(modifier = Modifier.height(8.dp))
        Text("UI Model (presentation):", fontWeight = FontWeight.Bold)
        Text("  displayName=${uiModel.displayName}")
        Text("  ${uiModel.emailLabel}")
        Text("  ${uiModel.memberSince}")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Entity -> toDomain() -> toUiModel(). Each layer owns its data shape.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
