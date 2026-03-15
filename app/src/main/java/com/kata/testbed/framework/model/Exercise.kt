package com.kata.testbed.framework.model

import androidx.compose.runtime.Composable

enum class Difficulty { BEGINNER, INTERMEDIATE, ADVANCED }

data class Exercise(
    val id: String,
    val section: Section,
    val number: Int,
    val title: String,
    val difficulty: Difficulty,
    val description: String,
    val instructions: String,
    val exerciseContent: @Composable () -> Unit,
    val answerContent: @Composable () -> Unit
)
