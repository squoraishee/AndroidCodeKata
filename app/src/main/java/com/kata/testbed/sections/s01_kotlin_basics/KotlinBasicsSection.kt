package com.kata.testbed.sections.s01_kotlin_basics

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s01_kotlin_basics.answers.*
import com.kata.testbed.sections.s01_kotlin_basics.exercises.*

fun registerKotlinBasics() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s01_e01",
            section = Section.KOTLIN_BASICS,
            number = 1,
            title = "Variables and Types",
            difficulty = Difficulty.BEGINNER,
            description = "Display val vs var values",
            instructions = "Declare a val (immutable) and a var (mutable).\nModify the var, then display both values using Text composables in a Column.",
            exerciseContent = { S01E01Exercise() },
            answerContent = { S01E01Answer() }
        ),
        Exercise(
            id = "s01_e02",
            section = Section.KOTLIN_BASICS,
            number = 2,
            title = "Null Safety",
            difficulty = Difficulty.BEGINNER,
            description = "Handle nullable types with safe calls and Elvis",
            instructions = "Declare nullable variables.\nUse safe call (?.), Elvis (?:), and let to handle nulls.\nDisplay the results in Text composables.",
            exerciseContent = { S01E02Exercise() },
            answerContent = { S01E02Answer() }
        ),
        Exercise(
            id = "s01_e03",
            section = Section.KOTLIN_BASICS,
            number = 3,
            title = "String Templates",
            difficulty = Difficulty.BEGINNER,
            description = "Build formatted strings with expressions",
            instructions = "Use string templates with \$variable and \${expression} syntax.\nFormat and display multiple strings showing different template features.",
            exerciseContent = { S01E03Exercise() },
            answerContent = { S01E03Answer() }
        ),
        Exercise(
            id = "s01_e04",
            section = Section.KOTLIN_BASICS,
            number = 4,
            title = "When Expressions",
            difficulty = Difficulty.BEGINNER,
            description = "Pattern match on input and show result",
            instructions = "Use when expressions to match values, ranges, and types.\nDisplay the matched results in Text composables.",
            exerciseContent = { S01E04Exercise() },
            answerContent = { S01E04Answer() }
        ),
        Exercise(
            id = "s01_e05",
            section = Section.KOTLIN_BASICS,
            number = 5,
            title = "Collections: List and Map",
            difficulty = Difficulty.BEGINNER,
            description = "Filter/transform and display a list",
            instructions = "Create a list of numbers and a map of names to ages.\nFilter, map, and transform them, then display the results.",
            exerciseContent = { S01E05Exercise() },
            answerContent = { S01E05Answer() }
        ),
        Exercise(
            id = "s01_e06",
            section = Section.KOTLIN_BASICS,
            number = 6,
            title = "Data Classes",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Create, copy, destructure a data class",
            instructions = "Define a data class with properties.\nDemonstrate copy(), destructuring, toString(), and equals().\nDisplay all results.",
            exerciseContent = { S01E06Exercise() },
            answerContent = { S01E06Answer() }
        ),
        Exercise(
            id = "s01_e07",
            section = Section.KOTLIN_BASICS,
            number = 7,
            title = "Sealed Classes",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Model Success/Error and render each case",
            instructions = "Create a sealed class for Result with Success and Error subclasses.\nUse when to exhaustively handle each case and display the outcome.",
            exerciseContent = { S01E07Exercise() },
            answerContent = { S01E07Answer() }
        ),
        Exercise(
            id = "s01_e08",
            section = Section.KOTLIN_BASICS,
            number = 8,
            title = "Extension Functions",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Add a method to String, display result",
            instructions = "Write extension functions on String and Int.\nCall them and display the results.",
            exerciseContent = { S01E08Exercise() },
            answerContent = { S01E08Answer() }
        ),
        Exercise(
            id = "s01_e09",
            section = Section.KOTLIN_BASICS,
            number = 9,
            title = "Higher-Order Functions",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Transform a list with a lambda",
            instructions = "Write a higher-order function that accepts a lambda parameter.\nUse it to transform a list and display before/after results.",
            exerciseContent = { S01E09Exercise() },
            answerContent = { S01E09Answer() }
        ),
        Exercise(
            id = "s01_e10",
            section = Section.KOTLIN_BASICS,
            number = 10,
            title = "Scope Functions",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Chain let/run/apply to configure an object",
            instructions = "Use let, run, apply, also, and with on objects.\nDisplay the configured results to show how each scope function works.",
            exerciseContent = { S01E10Exercise() },
            answerContent = { S01E10Answer() }
        ),
        Exercise(
            id = "s01_e11",
            section = Section.KOTLIN_BASICS,
            number = 11,
            title = "Generics",
            difficulty = Difficulty.ADVANCED,
            description = "Build a generic Stack, display push/pop results",
            instructions = "Create a generic Stack<T> class with push, pop, and peek.\nDemonstrate it with different types and display the operations.",
            exerciseContent = { S01E11Exercise() },
            answerContent = { S01E11Answer() }
        ),
        Exercise(
            id = "s01_e12",
            section = Section.KOTLIN_BASICS,
            number = 12,
            title = "Object and Companion",
            difficulty = Difficulty.ADVANCED,
            description = "Singleton counter with companion factory",
            instructions = "Create an object singleton and a class with a companion object factory.\nDemonstrate singleton state and factory method usage.",
            exerciseContent = { S01E12Exercise() },
            answerContent = { S01E12Answer() }
        ),
        Exercise(
            id = "s01_e13",
            section = Section.KOTLIN_BASICS,
            number = 13,
            title = "Delegation",
            difficulty = Difficulty.ADVANCED,
            description = "Property delegation with observable/vetoable",
            instructions = "Use Delegates.observable and Delegates.vetoable for property delegation.\nTrack changes and display the delegation behavior.",
            exerciseContent = { S01E13Exercise() },
            answerContent = { S01E13Answer() }
        ),
        Exercise(
            id = "s01_e14",
            section = Section.KOTLIN_BASICS,
            number = 14,
            title = "Inline and Reified",
            difficulty = Difficulty.ADVANCED,
            description = "Inline function with reified type check",
            instructions = "Write an inline function with a reified type parameter.\nUse it to filter a list by type and display the results.",
            exerciseContent = { S01E14Exercise() },
            answerContent = { S01E14Answer() }
        ),
        Exercise(
            id = "s01_e15",
            section = Section.KOTLIN_BASICS,
            number = 15,
            title = "DSL Building",
            difficulty = Difficulty.ADVANCED,
            description = "Small DSL that builds a UI description",
            instructions = "Build a small DSL using lambdas with receivers.\nCreate a menu builder DSL and display the constructed result.",
            exerciseContent = { S01E15Exercise() },
            answerContent = { S01E15Answer() }
        )
    ))
}
