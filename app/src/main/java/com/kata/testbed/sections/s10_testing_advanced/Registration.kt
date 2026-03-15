package com.kata.testbed.sections.s10_testing_advanced

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s10_testing_advanced.answers.*
import com.kata.testbed.sections.s10_testing_advanced.exercises.*

fun registerTestingAdvanced() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s10_e01",
            section = Section.TESTING_ADVANCED,
            number = 1,
            title = "First Unit Test",
            difficulty = Difficulty.BEGINNER,
            description = "JUnit test for pure function",
            instructions = "Write a pure function and its JUnit test.\nDisplay the test code and explain assertions with assertEquals, assertTrue, assertNotNull.",
            exerciseContent = { S10E01Exercise() },
            answerContent = { S10E01Answer() }
        ),
        Exercise(
            id = "s10_e02",
            section = Section.TESTING_ADVANCED,
            number = 2,
            title = "Testing with Fakes",
            difficulty = Difficulty.BEGINNER,
            description = "FakeRepository for ViewModel test",
            instructions = "Create a FakeRepository that implements a repository interface with in-memory data.\nShow how to use it in a ViewModel test.",
            exerciseContent = { S10E02Exercise() },
            answerContent = { S10E02Answer() }
        ),
        Exercise(
            id = "s10_e03",
            section = Section.TESTING_ADVANCED,
            number = 3,
            title = "Testing Coroutines",
            difficulty = Difficulty.BEGINNER,
            description = "runTest with TestDispatcher",
            instructions = "Show how to test suspend functions using runTest and TestDispatcher.\nDisplay the test code that advances virtual time.",
            exerciseContent = { S10E03Exercise() },
            answerContent = { S10E03Answer() }
        ),
        Exercise(
            id = "s10_e04",
            section = Section.TESTING_ADVANCED,
            number = 4,
            title = "Testing Flows",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Verify emissions with Turbine",
            instructions = "Show how to test Flow emissions using the Turbine library.\nDisplay test code that asserts on specific emissions and completion.",
            exerciseContent = { S10E04Exercise() },
            answerContent = { S10E04Answer() }
        ),
        Exercise(
            id = "s10_e05",
            section = Section.TESTING_ADVANCED,
            number = 5,
            title = "ViewModel State Test",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Assert UI state sequence",
            instructions = "Show a ViewModel test that verifies the sequence of UI states (Loading -> Success or Error).\nDisplay the test with state collection and assertions.",
            exerciseContent = { S10E05Exercise() },
            answerContent = { S10E05Answer() }
        ),
        Exercise(
            id = "s10_e06",
            section = Section.TESTING_ADVANCED,
            number = 6,
            title = "Compose Test Basics",
            difficulty = Difficulty.INTERMEDIATE,
            description = "ComposeTestRule find and assert",
            instructions = "Show how to write Compose UI tests using ComposeTestRule.\nDisplay test code that finds nodes by text and tag, and asserts visibility.",
            exerciseContent = { S10E06Exercise() },
            answerContent = { S10E06Answer() }
        ),
        Exercise(
            id = "s10_e07",
            section = Section.TESTING_ADVANCED,
            number = 7,
            title = "User Interaction Test",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Simulate clicks and text input",
            instructions = "Show Compose tests that simulate user interactions: performClick, performTextInput, performScrollTo.\nDisplay the test code with interaction and assertion steps.",
            exerciseContent = { S10E07Exercise() },
            answerContent = { S10E07Answer() }
        ),
        Exercise(
            id = "s10_e08",
            section = Section.TESTING_ADVANCED,
            number = 8,
            title = "Navigation Test",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Verify navigation events",
            instructions = "Show how to test navigation by verifying that navigation events are triggered correctly.\nDisplay test code that asserts on destination routes.",
            exerciseContent = { S10E08Exercise() },
            answerContent = { S10E08Answer() }
        ),
        Exercise(
            id = "s10_e09",
            section = Section.TESTING_ADVANCED,
            number = 9,
            title = "Screenshot Test",
            difficulty = Difficulty.ADVANCED,
            description = "Compose screenshot test",
            instructions = "Show how to set up a Compose screenshot test for visual regression testing.\nDisplay the test code that captures and compares composable screenshots.",
            exerciseContent = { S10E09Exercise() },
            answerContent = { S10E09Answer() }
        ),
        Exercise(
            id = "s10_e10",
            section = Section.TESTING_ADVANCED,
            number = 10,
            title = "Custom Modifier",
            difficulty = Difficulty.ADVANCED,
            description = "Modifier that measures performance",
            instructions = "Create a custom Modifier that measures and displays the size of a composable.\nApply it to several composables and show the measured dimensions.",
            exerciseContent = { S10E10Exercise() },
            answerContent = { S10E10Answer() }
        ),
        Exercise(
            id = "s10_e11",
            section = Section.TESTING_ADVANCED,
            number = 11,
            title = "CompositionLocal",
            difficulty = Difficulty.ADVANCED,
            description = "Custom CompositionLocal value",
            instructions = "Create a custom CompositionLocal for a theme accent color.\nProvide different values at different levels of the tree and display them.",
            exerciseContent = { S10E11Exercise() },
            answerContent = { S10E11Answer() }
        ),
        Exercise(
            id = "s10_e12",
            section = Section.TESTING_ADVANCED,
            number = 12,
            title = "produceState",
            difficulty = Difficulty.ADVANCED,
            description = "Convert suspend to compose state",
            instructions = "Use produceState to convert a suspend function into Compose state.\nSimulate loading data asynchronously and display loading/success states.",
            exerciseContent = { S10E12Exercise() },
            answerContent = { S10E12Answer() }
        ),
        Exercise(
            id = "s10_e13",
            section = Section.TESTING_ADVANCED,
            number = 13,
            title = "Custom Layout",
            difficulty = Difficulty.ADVANCED,
            description = "Circular layout composable",
            instructions = "Create a custom Layout composable that positions children in a circle.\nPlace colored boxes around the circle and display the result.",
            exerciseContent = { S10E13Exercise() },
            answerContent = { S10E13Answer() }
        ),
        Exercise(
            id = "s10_e14",
            section = Section.TESTING_ADVANCED,
            number = 14,
            title = "Gesture Detection",
            difficulty = Difficulty.ADVANCED,
            description = "Multi-touch with detectTransformGestures",
            instructions = "Create a composable that detects transform gestures (pan, zoom, rotate).\nDisplay the current transformation values as the user interacts.",
            exerciseContent = { S10E14Exercise() },
            answerContent = { S10E14Answer() }
        ),
        Exercise(
            id = "s10_e15",
            section = Section.TESTING_ADVANCED,
            number = 15,
            title = "Animation Choreography",
            difficulty = Difficulty.ADVANCED,
            description = "Animatable with coroutines",
            instructions = "Use Animatable to choreograph a sequence of animations with coroutines.\nAnimate position, color, and size in a coordinated sequence.",
            exerciseContent = { S10E15Exercise() },
            answerContent = { S10E15Answer() }
        )
    ))
}
