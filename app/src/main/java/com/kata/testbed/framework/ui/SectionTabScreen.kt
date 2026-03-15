package com.kata.testbed.framework.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry

@Composable
fun SectionTabScreen(modifier: Modifier = Modifier) {
    val sections = Section.entries
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        ScrollableTabRow(selectedTabIndex = selectedIndex) {
            sections.forEachIndexed { index, section ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = { selectedIndex = index },
                    text = { Text(section.title) }
                )
            }
        }

        val currentSection = sections[selectedIndex]
        val exercises = ExerciseRegistry.getBySection(currentSection)
        ExerciseListScreen(exercises = exercises)
    }
}
