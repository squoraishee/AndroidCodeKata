package com.kata.testbed.sections.s09_clean_architecture.answers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// === Feature: Profile ===

// Domain model
private data class Profile(val id: String, val username: String, val bio: String)

// Domain repository interface
private interface ProfileRepository {
    fun getProfile(id: String): Profile?
}

// Data layer implementation
private class InMemoryProfileRepository : ProfileRepository {
    private val profiles = listOf(
        Profile("1", "alice_dev", "Kotlin enthusiast"),
        Profile("2", "bob_coder", "Compose advocate")
    )
    override fun getProfile(id: String): Profile? = profiles.find { it.id == id }
}

// Use case
private class GetProfileUseCase(private val repo: ProfileRepository) {
    operator fun invoke(id: String): Profile? = repo.getProfile(id)
}

// UI state
private sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Loaded(val username: String, val bio: String) : ProfileUiState
    data class NotFound(val id: String) : ProfileUiState
}

@Composable
private fun LayerBox(label: String, color: Color, detail: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)) {
        Spacer(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("$label: ", fontWeight = FontWeight.Bold, fontSize = 13.sp)
        Text(detail, fontSize = 13.sp)
    }
}

@Composable
fun S09E13Answer() {
    val repo: ProfileRepository = InMemoryProfileRepository()
    val getProfile = GetProfileUseCase(repo)

    val profile = getProfile("1")
    val uiState: ProfileUiState = if (profile != null) {
        ProfileUiState.Loaded(profile.username, profile.bio)
    } else {
        ProfileUiState.NotFound("1")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Feature Module Boundary", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("A self-contained feature with its own model, repo, use case, and UI state.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("Profile Feature Architecture:", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        LayerBox("Domain Model", Color(0xFF1565C0), "Profile(id, username, bio)")
        LayerBox("Repository Interface", Color(0xFF1565C0), "ProfileRepository")
        LayerBox("Repository Impl", Color(0xFF2E7D32), "InMemoryProfileRepository")
        LayerBox("Use Case", Color(0xFFE65100), "GetProfileUseCase")
        LayerBox("UI State", Color(0xFF6A1B9A), "ProfileUiState sealed interface")

        Spacer(modifier = Modifier.height(12.dp))
        Text("Live result:", fontWeight = FontWeight.Bold)
        when (uiState) {
            is ProfileUiState.Loading -> Text("  Loading...")
            is ProfileUiState.Loaded -> {
                Text("  @${uiState.username}")
                Text("  ${uiState.bio}")
            }
            is ProfileUiState.NotFound -> Text("  Profile ${uiState.id} not found")
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Everything the feature needs is in one package. Other features depend on domain interfaces only.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
