package com.kata.testbed.framework.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise

@Composable
fun ExerciseListScreen(exercises: List<Exercise>) {
    var showExerciseDialog by remember { mutableStateOf<Exercise?>(null) }
    var showAnswerDialog by remember { mutableStateOf<Exercise?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 12.dp)
    ) {
        items(exercises, key = { it.id }) { exercise ->
            ExerciseCard(
                exercise = exercise,
                onExercise = { showExerciseDialog = exercise },
                onAnswer = { showAnswerDialog = exercise }
            )
        }
    }

    showExerciseDialog?.let { exercise ->
        ExerciseDialog(exercise = exercise, onDismiss = { showExerciseDialog = null })
    }
    showAnswerDialog?.let { exercise ->
        AnswerDialog(exercise = exercise, onDismiss = { showAnswerDialog = null })
    }
}

@Composable
private fun ExerciseCard(
    exercise: Exercise,
    onExercise: () -> Unit,
    onAnswer: () -> Unit
) {
    val difficultyColor = when (exercise.difficulty) {
        Difficulty.BEGINNER -> MaterialTheme.colorScheme.primary
        Difficulty.INTERMEDIATE -> MaterialTheme.colorScheme.tertiary
        Difficulty.ADVANCED -> MaterialTheme.colorScheme.error
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#${exercise.number}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = exercise.difficulty.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = difficultyColor
                )
            }
            Text(
                text = exercise.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = exercise.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(onClick = onExercise, modifier = Modifier.weight(1f)) {
                    Text("Exercise")
                }
                FilledTonalButton(onClick = onAnswer, modifier = Modifier.weight(1f)) {
                    Text("Answer")
                }
            }
        }
    }
}
