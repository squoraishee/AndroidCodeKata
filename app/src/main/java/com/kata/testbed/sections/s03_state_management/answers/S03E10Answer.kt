package com.kata.testbed.sections.s03_state_management.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class S03E10UserProfile(
    val name: String = "",
    val age: String = "",
    val city: String = ""
)

// mapSaver converts a complex object to/from a Map<String, Any?> for Bundle storage.
// This lets rememberSaveable persist custom data classes across config changes.
val S03E10UserProfileSaver: Saver<MutableState<S03E10UserProfile>, *> = mapSaver(
    save = {
        val profile = it.value
        mapOf("name" to profile.name, "age" to profile.age, "city" to profile.city)
    },
    restore = {
        mutableStateOf(
            S03E10UserProfile(
                name = it["name"] as String,
                age = it["age"] as String,
                city = it["city"] as String
            )
        )
    }
)

@Composable
fun S03E10Answer() {
    var profile by rememberSaveable(saver = S03E10UserProfileSaver) {
        mutableStateOf(S03E10UserProfile())
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = profile.name,
            onValueChange = { profile = profile.copy(name = it) },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = profile.age,
            onValueChange = { profile = profile.copy(age = it) },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = profile.city,
            onValueChange = { profile = profile.copy(city = it) },
            label = { Text("City") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text("Current profile:", style = MaterialTheme.typography.titleSmall)
        Text("Name: ${profile.name.ifBlank { "(empty)" }}")
        Text("Age: ${profile.age.ifBlank { "(empty)" }}")
        Text("City: ${profile.city.ifBlank { "(empty)" }}")

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Custom Savers (mapSaver / listSaver) let rememberSaveable persist " +
                "complex objects that aren't directly Bundle-compatible.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
