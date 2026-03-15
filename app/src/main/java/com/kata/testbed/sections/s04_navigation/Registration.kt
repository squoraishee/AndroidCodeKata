package com.kata.testbed.sections.s04_navigation

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s04_navigation.answers.*
import com.kata.testbed.sections.s04_navigation.exercises.*

fun registerNavigation() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s04_e01",
            section = Section.NAVIGATION,
            number = 1,
            title = "Basic NavHost",
            difficulty = Difficulty.BEGINNER,
            description = "Two screens with navigate()",
            instructions = "Simulate a NavHost with two screens: Home and Detail.\nUse state to track the current screen and buttons to navigate between them.",
            exerciseContent = { S04E01Exercise() },
            answerContent = { S04E01Answer() }
        ),
        Exercise(
            id = "s04_e02",
            section = Section.NAVIGATION,
            number = 2,
            title = "Route Arguments",
            difficulty = Difficulty.BEGINNER,
            description = "Pass string ID between screens",
            instructions = "Simulate passing an argument from a list screen to a detail screen.\nClick an item to navigate to detail with the item's ID.",
            exerciseContent = { S04E02Exercise() },
            answerContent = { S04E02Answer() }
        ),
        Exercise(
            id = "s04_e03",
            section = Section.NAVIGATION,
            number = 3,
            title = "Type-Safe Routes",
            difficulty = Difficulty.BEGINNER,
            description = "Serializable route objects",
            instructions = "Define route classes (data objects and data classes) instead of string routes.\nSimulate type-safe navigation between screens.",
            exerciseContent = { S04E03Exercise() },
            answerContent = { S04E03Answer() }
        ),
        Exercise(
            id = "s04_e04",
            section = Section.NAVIGATION,
            number = 4,
            title = "Bottom Navigation",
            difficulty = Difficulty.INTERMEDIATE,
            description = "NavigationBar with 3 tabs",
            instructions = "Create a bottom navigation bar with Home, Search, and Profile tabs.\nEach tab shows different content. Track the selected tab with state.",
            exerciseContent = { S04E04Exercise() },
            answerContent = { S04E04Answer() }
        ),
        Exercise(
            id = "s04_e05",
            section = Section.NAVIGATION,
            number = 5,
            title = "Nested Nav Graphs",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Auth and main flow separation",
            instructions = "Simulate nested navigation graphs: an Auth flow (Login -> Register) and a Main flow (Home -> Settings).\nShow how they are separate but connected.",
            exerciseContent = { S04E05Exercise() },
            answerContent = { S04E05Answer() }
        ),
        Exercise(
            id = "s04_e06",
            section = Section.NAVIGATION,
            number = 6,
            title = "Deep Links",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Handle deep link to specific screen",
            instructions = "Simulate a deep link handler that navigates directly to a specific screen.\nProvide a text field for the deep link path and resolve it to the correct screen.",
            exerciseContent = { S04E06Exercise() },
            answerContent = { S04E06Answer() }
        ),
        Exercise(
            id = "s04_e07",
            section = Section.NAVIGATION,
            number = 7,
            title = "Navigation Results",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Return data from screen B to A",
            instructions = "Simulate returning a result from a second screen back to the first.\nScreen A navigates to Screen B, which returns a selected value.",
            exerciseContent = { S04E07Exercise() },
            answerContent = { S04E07Answer() }
        ),
        Exercise(
            id = "s04_e08",
            section = Section.NAVIGATION,
            number = 8,
            title = "Lifecycle Observer",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Log lifecycle events in composable",
            instructions = "Use LocalLifecycleOwner and a LifecycleEventObserver to track lifecycle events.\nDisplay a log of events as they occur.",
            exerciseContent = { S04E08Exercise() },
            answerContent = { S04E08Answer() }
        ),
        Exercise(
            id = "s04_e09",
            section = Section.NAVIGATION,
            number = 9,
            title = "DisposableEffect",
            difficulty = Difficulty.ADVANCED,
            description = "Register/unregister listener on lifecycle",
            instructions = "Use DisposableEffect to register a listener when the composable enters composition and unregister when it leaves.\nShow the registration status.",
            exerciseContent = { S04E09Exercise() },
            answerContent = { S04E09Answer() }
        ),
        Exercise(
            id = "s04_e10",
            section = Section.NAVIGATION,
            number = 10,
            title = "LaunchedEffect",
            difficulty = Difficulty.ADVANCED,
            description = "One-time side effect on key change",
            instructions = "Use LaunchedEffect to load data when a selected ID changes.\nSimulate an async load with delay and show loading/loaded states.",
            exerciseContent = { S04E10Exercise() },
            answerContent = { S04E10Answer() }
        ),
        Exercise(
            id = "s04_e11",
            section = Section.NAVIGATION,
            number = 11,
            title = "Back Handler",
            difficulty = Difficulty.ADVANCED,
            description = "Custom back button behavior",
            instructions = "Simulate custom back handling. When on a detail screen, the back button should show a confirmation before navigating back.",
            exerciseContent = { S04E11Exercise() },
            answerContent = { S04E11Answer() }
        ),
        Exercise(
            id = "s04_e12",
            section = Section.NAVIGATION,
            number = 12,
            title = "Conditional Navigation",
            difficulty = Difficulty.ADVANCED,
            description = "Redirect to login if unauthenticated",
            instructions = "Simulate an auth guard: if the user is not logged in, redirect to a login screen.\nAfter login, navigate to the protected content.",
            exerciseContent = { S04E12Exercise() },
            answerContent = { S04E12Answer() }
        ),
        Exercise(
            id = "s04_e13",
            section = Section.NAVIGATION,
            number = 13,
            title = "Transition Animations",
            difficulty = Difficulty.ADVANCED,
            description = "Enter/exit animation on navigation",
            instructions = "Simulate screen transitions with enter and exit animations.\nUse AnimatedContent to slide screens in and out.",
            exerciseContent = { S04E13Exercise() },
            answerContent = { S04E13Answer() }
        ),
        Exercise(
            id = "s04_e14",
            section = Section.NAVIGATION,
            number = 14,
            title = "Shared Element Transition",
            difficulty = Difficulty.ADVANCED,
            description = "Animate shared element between screens",
            instructions = "Simulate a shared element transition: an item expands from a list into a detail view.\nUse animated bounds/size to show the concept.",
            exerciseContent = { S04E14Exercise() },
            answerContent = { S04E14Answer() }
        ),
        Exercise(
            id = "s04_e15",
            section = Section.NAVIGATION,
            number = 15,
            title = "Multi-Module Navigation",
            difficulty = Difficulty.ADVANCED,
            description = "Navigation contract across modules",
            instructions = "Simulate multi-module navigation by defining route interfaces that modules implement.\nShow how modules register their screens without depending on each other.",
            exerciseContent = { S04E15Exercise() },
            answerContent = { S04E15Answer() }
        )
    ))
}
