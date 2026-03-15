package com.kata.testbed.framework.registry

import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section

object ExerciseRegistry {
    private val exercises = mutableListOf<Exercise>()

    fun register(exercise: Exercise) {
        exercises.add(exercise)
    }

    fun registerAll(list: List<Exercise>) {
        exercises.addAll(list)
    }

    fun getBySection(section: Section): List<Exercise> =
        exercises.filter { it.section == section }.sortedBy { it.number }

    fun all(): List<Exercise> = exercises.toList()
}
