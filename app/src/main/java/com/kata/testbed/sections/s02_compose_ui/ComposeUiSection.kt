package com.kata.testbed.sections.s02_compose_ui

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s02_compose_ui.answers.*
import com.kata.testbed.sections.s02_compose_ui.exercises.*

fun registerComposeUi() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s02_e01",
            section = Section.COMPOSE_UI,
            number = 1,
            title = "Hello Text",
            difficulty = Difficulty.BEGINNER,
            description = "Style text with fontSize, color, fontWeight",
            instructions = "Display multiple Text composables with different styles.\nUse fontSize, color, fontWeight, and fontStyle properties.",
            exerciseContent = { S02E01Exercise() },
            answerContent = { S02E01Answer() }
        ),
        Exercise(
            id = "s02_e02",
            section = Section.COMPOSE_UI,
            number = 2,
            title = "Column and Row",
            difficulty = Difficulty.BEGINNER,
            description = "Arrange items with spacing and alignment",
            instructions = "Use Column and Row to arrange Text and other composables.\nApply verticalArrangement, horizontalAlignment, and spacing.",
            exerciseContent = { S02E02Exercise() },
            answerContent = { S02E02Answer() }
        ),
        Exercise(
            id = "s02_e03",
            section = Section.COMPOSE_UI,
            number = 3,
            title = "Box Layout",
            difficulty = Difficulty.BEGINNER,
            description = "Overlay elements with Box and alignment",
            instructions = "Use Box to stack elements on top of each other.\nPosition children using Alignment modifiers.",
            exerciseContent = { S02E03Exercise() },
            answerContent = { S02E03Answer() }
        ),
        Exercise(
            id = "s02_e04",
            section = Section.COMPOSE_UI,
            number = 4,
            title = "Modifier Chains",
            difficulty = Difficulty.BEGINNER,
            description = "Apply padding, background, border, clip",
            instructions = "Chain multiple modifiers on composables.\nDemonstrate that modifier order matters by showing different results.",
            exerciseContent = { S02E04Exercise() },
            answerContent = { S02E04Answer() }
        ),
        Exercise(
            id = "s02_e05",
            section = Section.COMPOSE_UI,
            number = 5,
            title = "Button Styles",
            difficulty = Difficulty.BEGINNER,
            description = "Filled, outlined, text buttons with click handlers",
            instructions = "Create Button, OutlinedButton, and TextButton composables.\nAdd click handlers that update displayed text.",
            exerciseContent = { S02E05Exercise() },
            answerContent = { S02E05Answer() }
        ),
        Exercise(
            id = "s02_e06",
            section = Section.COMPOSE_UI,
            number = 6,
            title = "TextField Input",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Input with label, placeholder, value handling",
            instructions = "Create TextField and OutlinedTextField with labels and placeholders.\nManage input state with remember and mutableStateOf.",
            exerciseContent = { S02E06Exercise() },
            answerContent = { S02E06Answer() }
        ),
        Exercise(
            id = "s02_e07",
            section = Section.COMPOSE_UI,
            number = 7,
            title = "Image and Icon",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Display Material icons with tint and size",
            instructions = "Display Material icons using the Icon composable.\nApply different tints, sizes, and content descriptions.",
            exerciseContent = { S02E07Exercise() },
            answerContent = { S02E07Answer() }
        ),
        Exercise(
            id = "s02_e08",
            section = Section.COMPOSE_UI,
            number = 8,
            title = "Card and Surface",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Elevated card with rounded corners",
            instructions = "Create Card and ElevatedCard composables with content.\nCustomize shape, elevation, and colors.",
            exerciseContent = { S02E08Exercise() },
            answerContent = { S02E08Answer() }
        ),
        Exercise(
            id = "s02_e09",
            section = Section.COMPOSE_UI,
            number = 9,
            title = "Scaffold and TopAppBar",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Screen layout with app bar and FAB",
            instructions = "Build a Scaffold with TopAppBar and FloatingActionButton.\nAdd content in the scaffold body area.",
            exerciseContent = { S02E09Exercise() },
            answerContent = { S02E09Answer() }
        ),
        Exercise(
            id = "s02_e10",
            section = Section.COMPOSE_UI,
            number = 10,
            title = "Canvas Drawing",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Draw shapes with Canvas composable",
            instructions = "Use the Canvas composable to draw circles, rectangles, and lines.\nApply different colors and stroke styles.",
            exerciseContent = { S02E10Exercise() },
            answerContent = { S02E10Answer() }
        ),
        Exercise(
            id = "s02_e11",
            section = Section.COMPOSE_UI,
            number = 11,
            title = "ConstraintLayout",
            difficulty = Difficulty.ADVANCED,
            description = "Position elements with constraints (simulated with Row/Column)",
            instructions = "Simulate constraint-like positioning using nested Row and Column.\nPosition elements relative to each other with weights and alignment.",
            exerciseContent = { S02E11Exercise() },
            answerContent = { S02E11Answer() }
        ),
        Exercise(
            id = "s02_e12",
            section = Section.COMPOSE_UI,
            number = 12,
            title = "Custom Theme",
            difficulty = Difficulty.ADVANCED,
            description = "Define and apply a custom MaterialTheme",
            instructions = "Create a custom color scheme and apply it with MaterialTheme.\nShow themed components inside the custom theme.",
            exerciseContent = { S02E12Exercise() },
            answerContent = { S02E12Answer() }
        ),
        Exercise(
            id = "s02_e13",
            section = Section.COMPOSE_UI,
            number = 13,
            title = "Typography Scale",
            difficulty = Difficulty.ADVANCED,
            description = "Custom fonts and type scale",
            instructions = "Define a custom Typography with different text styles.\nDisplay text using each style from the custom type scale.",
            exerciseContent = { S02E13Exercise() },
            answerContent = { S02E13Answer() }
        ),
        Exercise(
            id = "s02_e14",
            section = Section.COMPOSE_UI,
            number = 14,
            title = "Adaptive Layout",
            difficulty = Difficulty.ADVANCED,
            description = "Responsive layout with BoxWithConstraints",
            instructions = "Use BoxWithConstraints to check available space.\nSwitch between layouts based on width constraints.",
            exerciseContent = { S02E14Exercise() },
            answerContent = { S02E14Answer() }
        ),
        Exercise(
            id = "s02_e15",
            section = Section.COMPOSE_UI,
            number = 15,
            title = "Slot-Based Component",
            difficulty = Difficulty.ADVANCED,
            description = "Composable with header/content/footer slots",
            instructions = "Build a reusable composable that accepts slot parameters.\nPass header, content, and footer composables as arguments.",
            exerciseContent = { S02E15Exercise() },
            answerContent = { S02E15Answer() }
        )
    ))
}
