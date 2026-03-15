package com.kata.testbed.sections.s03_state_management

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s03_state_management.answers.*
import com.kata.testbed.sections.s03_state_management.exercises.*

fun registerStateManagement() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s03_e01",
            section = Section.STATE_MANAGEMENT,
            number = 1,
            title = "remember and mutableStateOf",
            difficulty = Difficulty.BEGINNER,
            description = "Counter with local state",
            instructions = "Create a counter that increments when a button is clicked.\nUse remember and mutableStateOf to hold the count.",
            exerciseContent = { S03E01Exercise() },
            answerContent = { S03E01Answer() }
        ),
        Exercise(
            id = "s03_e02",
            section = Section.STATE_MANAGEMENT,
            number = 2,
            title = "State Hoisting",
            difficulty = Difficulty.BEGINNER,
            description = "Extract state to caller composable",
            instructions = "Create a stateless counter composable that receives count and onIncrement as parameters.\nThe parent composable owns the state and passes it down.",
            exerciseContent = { S03E02Exercise() },
            answerContent = { S03E02Answer() }
        ),
        Exercise(
            id = "s03_e03",
            section = Section.STATE_MANAGEMENT,
            number = 3,
            title = "rememberSaveable",
            difficulty = Difficulty.BEGINNER,
            description = "Survive configuration changes",
            instructions = "Create a text field whose value persists across configuration changes.\nUse rememberSaveable instead of remember.",
            exerciseContent = { S03E03Exercise() },
            answerContent = { S03E03Answer() }
        ),
        Exercise(
            id = "s03_e04",
            section = Section.STATE_MANAGEMENT,
            number = 4,
            title = "Derived State",
            difficulty = Difficulty.INTERMEDIATE,
            description = "derivedStateOf for filtered list",
            instructions = "Create a list of items with a search field.\nUse derivedStateOf to compute the filtered list only when the search query or items change.",
            exerciseContent = { S03E04Exercise() },
            answerContent = { S03E04Answer() }
        ),
        Exercise(
            id = "s03_e05",
            section = Section.STATE_MANAGEMENT,
            number = 5,
            title = "Multiple State Fields",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Form with interdependent fields",
            instructions = "Create a form with name, email, and a submit button.\nThe submit button should only be enabled when both fields are non-empty.\nShow a greeting message after submission.",
            exerciseContent = { S03E05Exercise() },
            answerContent = { S03E05Answer() }
        ),
        Exercise(
            id = "s03_e06",
            section = Section.STATE_MANAGEMENT,
            number = 6,
            title = "ViewModel StateFlow",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Move state to ViewModel",
            instructions = "Create a simple state holder class (simulating a ViewModel) that exposes a StateFlow of a counter.\nCollect the flow in a composable using collectAsState().",
            exerciseContent = { S03E06Exercise() },
            answerContent = { S03E06Answer() }
        ),
        Exercise(
            id = "s03_e07",
            section = Section.STATE_MANAGEMENT,
            number = 7,
            title = "UiState Sealed Class",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Loading/Success/Error sealed interface",
            instructions = "Define a sealed interface UiState with Loading, Success(data), and Error(message) variants.\nSimulate loading data and display the appropriate UI for each state.",
            exerciseContent = { S03E07Exercise() },
            answerContent = { S03E07Answer() }
        ),
        Exercise(
            id = "s03_e08",
            section = Section.STATE_MANAGEMENT,
            number = 8,
            title = "One-Shot Events",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Separate events from persistent state",
            instructions = "Create a form that shows a snackbar-like message on submit.\nUse a separate event channel (simulated) so the message shows once, not on every recomposition.",
            exerciseContent = { S03E08Exercise() },
            answerContent = { S03E08Answer() }
        ),
        Exercise(
            id = "s03_e09",
            section = Section.STATE_MANAGEMENT,
            number = 9,
            title = "Snapshot Flow",
            difficulty = Difficulty.ADVANCED,
            description = "Convert compose state to Flow",
            instructions = "Use snapshotFlow to observe a compose state value as a Flow.\nDebounce the flow and display the debounced value.",
            exerciseContent = { S03E09Exercise() },
            answerContent = { S03E09Answer() }
        ),
        Exercise(
            id = "s03_e10",
            section = Section.STATE_MANAGEMENT,
            number = 10,
            title = "Custom Saver",
            difficulty = Difficulty.ADVANCED,
            description = "Save/restore complex state",
            instructions = "Create a data class with multiple fields and a custom Saver.\nUse rememberSaveable with the custom saver to persist the state.",
            exerciseContent = { S03E10Exercise() },
            answerContent = { S03E10Answer() }
        ),
        Exercise(
            id = "s03_e11",
            section = Section.STATE_MANAGEMENT,
            number = 11,
            title = "Shared ViewModel",
            difficulty = Difficulty.ADVANCED,
            description = "Share state across composables",
            instructions = "Create a shared state holder that two sibling composables both read from and write to.\nChanges in one composable should reflect in the other.",
            exerciseContent = { S03E11Exercise() },
            answerContent = { S03E11Answer() }
        ),
        Exercise(
            id = "s03_e12",
            section = Section.STATE_MANAGEMENT,
            number = 12,
            title = "Recomposition Optimization",
            difficulty = Difficulty.ADVANCED,
            description = "Reduce recompositions with keys",
            instructions = "Create a list where each item has a stable key.\nUse key() composable to help Compose skip unnecessary recompositions when the list is reordered.",
            exerciseContent = { S03E12Exercise() },
            answerContent = { S03E12Answer() }
        ),
        Exercise(
            id = "s03_e13",
            section = Section.STATE_MANAGEMENT,
            number = 13,
            title = "Immutable State",
            difficulty = Difficulty.ADVANCED,
            description = "Persistent collections for state",
            instructions = "Demonstrate the difference between using a mutable list vs creating new list copies for state updates.\nShow how Compose detects changes when state is truly immutable.",
            exerciseContent = { S03E13Exercise() },
            answerContent = { S03E13Answer() }
        ),
        Exercise(
            id = "s03_e14",
            section = Section.STATE_MANAGEMENT,
            number = 14,
            title = "State Machine",
            difficulty = Difficulty.ADVANCED,
            description = "Finite state machine for multi-step form",
            instructions = "Create a multi-step wizard with states: Input -> Review -> Confirm -> Done.\nEach state shows different UI. Only valid transitions are allowed.",
            exerciseContent = { S03E14Exercise() },
            answerContent = { S03E14Answer() }
        ),
        Exercise(
            id = "s03_e15",
            section = Section.STATE_MANAGEMENT,
            number = 15,
            title = "MVI Pattern",
            difficulty = Difficulty.ADVANCED,
            description = "Full Intent -> Model -> View loop",
            instructions = "Implement MVI: define Intents (user actions), a Model (state), and a reduce function.\nDispatch intents to update the model and render the view from the model.",
            exerciseContent = { S03E15Exercise() },
            answerContent = { S03E15Answer() }
        )
    ))
}
