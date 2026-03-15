package com.kata.testbed.sections.s04_navigation.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private enum class Tab(val label: String) {
    HOME("Home"),
    SEARCH("Search"),
    PROFILE("Profile")
}

@Composable
fun S04E04Answer() {
    var selectedTab by remember { mutableStateOf(Tab.HOME) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Content area changes based on selected tab
        Column(
            modifier = Modifier
                .weight(1f, fill = false)
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            when (selectedTab) {
                Tab.HOME -> {
                    Text("Home", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Welcome to the home feed!")
                    Text("Scroll through your personalized content here.")
                }
                Tab.SEARCH -> {
                    Text("Search", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Search for anything.")
                    Text("Try typing in the search bar above.")
                }
                Tab.PROFILE -> {
                    Text("Profile", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Your profile settings.")
                    Text("Edit your name, photo, and preferences.")
                }
            }
        }

        // Bottom navigation bar
        NavigationBar {
            Tab.entries.forEach { tab ->
                NavigationBarItem(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    icon = { },
                    label = { Text(tab.label) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "NavigationBar with NavigationBarItem creates a Material 3 bottom nav. " +
                "Each tab maps to a nav graph destination. " +
                "Use saveState/restoreState to preserve tab back stacks.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
