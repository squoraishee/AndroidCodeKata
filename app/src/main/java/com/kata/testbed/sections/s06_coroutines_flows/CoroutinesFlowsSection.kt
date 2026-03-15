package com.kata.testbed.sections.s06_coroutines_flows

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s06_coroutines_flows.answers.*
import com.kata.testbed.sections.s06_coroutines_flows.exercises.*

fun registerCoroutinesFlows() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s06_e01",
            section = Section.COROUTINES_FLOWS,
            number = 1,
            title = "First Suspend Function",
            difficulty = Difficulty.BEGINNER,
            description = "Delay and return a value",
            instructions = "Write a suspend function that delays for 1 second, then returns a greeting string.\nCall it from a LaunchedEffect and display the result in a Text composable.\nShow a loading indicator while waiting.",
            exerciseContent = { S06E01Exercise() },
            answerContent = { S06E01Answer() }
        ),
        Exercise(
            id = "s06_e02",
            section = Section.COROUTINES_FLOWS,
            number = 2,
            title = "Launch and Async",
            difficulty = Difficulty.BEGINNER,
            description = "Run two tasks concurrently",
            instructions = "Use async to run two simulated API calls concurrently.\nEach call should delay for a different duration.\nDisplay both results and the total elapsed time to show concurrency.",
            exerciseContent = { S06E02Exercise() },
            answerContent = { S06E02Answer() }
        ),
        Exercise(
            id = "s06_e03",
            section = Section.COROUTINES_FLOWS,
            number = 3,
            title = "Structured Concurrency",
            difficulty = Difficulty.BEGINNER,
            description = "coroutineScope waits for all children",
            instructions = "Use coroutineScope to launch multiple child coroutines.\nDemonstrate that the parent waits for all children to complete.\nDisplay a log of events showing the execution order.",
            exerciseContent = { S06E03Exercise() },
            answerContent = { S06E03Answer() }
        ),
        Exercise(
            id = "s06_e04",
            section = Section.COROUTINES_FLOWS,
            number = 4,
            title = "Exception Handling",
            difficulty = Difficulty.INTERMEDIATE,
            description = "try/catch with SupervisorJob",
            instructions = "Demonstrate exception handling in coroutines.\nShow that a failed child cancels siblings by default.\nThen use supervisorScope so a failed child does not cancel siblings.\nDisplay the results of both approaches.",
            exerciseContent = { S06E04Exercise() },
            answerContent = { S06E04Answer() }
        ),
        Exercise(
            id = "s06_e05",
            section = Section.COROUTINES_FLOWS,
            number = 5,
            title = "Cancellation",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Cooperative cancellation with isActive",
            instructions = "Launch a coroutine that counts up every 500ms.\nAdd a Cancel button to cancel the coroutine.\nUse isActive or ensureActive() to check for cancellation.\nDisplay the count and whether the coroutine is running.",
            exerciseContent = { S06E05Exercise() },
            answerContent = { S06E05Answer() }
        ),
        Exercise(
            id = "s06_e06",
            section = Section.COROUTINES_FLOWS,
            number = 6,
            title = "Flow Basics",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Emit values with delay",
            instructions = "Create a flow that emits numbers 1 through 5 with a 500ms delay between each.\nCollect the flow in a LaunchedEffect and display each emitted value.\nShow the collected values as they arrive.",
            exerciseContent = { S06E06Exercise() },
            answerContent = { S06E06Answer() }
        ),
        Exercise(
            id = "s06_e07",
            section = Section.COROUTINES_FLOWS,
            number = 7,
            title = "Flow Operators",
            difficulty = Difficulty.INTERMEDIATE,
            description = "map, filter, take on a flow",
            instructions = "Create a flow of numbers 1 through 20.\nApply map to square each number, filter to keep only even results, and take(5) to limit output.\nCollect and display the transformed results.",
            exerciseContent = { S06E07Exercise() },
            answerContent = { S06E07Answer() }
        ),
        Exercise(
            id = "s06_e08",
            section = Section.COROUTINES_FLOWS,
            number = 8,
            title = "StateFlow in ViewModel",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Collect StateFlow in a composable",
            instructions = "Create a simple counter using MutableStateFlow inside a class.\nExpose it as StateFlow.\nCollect it in the composable using collectAsState().\nAdd increment and decrement buttons.",
            exerciseContent = { S06E08Exercise() },
            answerContent = { S06E08Answer() }
        ),
        Exercise(
            id = "s06_e09",
            section = Section.COROUTINES_FLOWS,
            number = 9,
            title = "SharedFlow Events",
            difficulty = Difficulty.ADVANCED,
            description = "Broadcast events to multiple collectors",
            instructions = "Create a MutableSharedFlow for one-shot events (e.g., snackbar messages).\nEmit events from button clicks.\nCollect events in a LaunchedEffect and display them in a list.\nShow that SharedFlow does not replay by default.",
            exerciseContent = { S06E09Exercise() },
            answerContent = { S06E09Answer() }
        ),
        Exercise(
            id = "s06_e10",
            section = Section.COROUTINES_FLOWS,
            number = 10,
            title = "Combine and Zip",
            difficulty = Difficulty.ADVANCED,
            description = "Merge two flows together",
            instructions = "Create two flows that emit at different rates.\nUse combine to merge them (latest from each).\nUse zip to pair them (one-to-one).\nDisplay the results of both approaches side by side.",
            exerciseContent = { S06E10Exercise() },
            answerContent = { S06E10Answer() }
        ),
        Exercise(
            id = "s06_e11",
            section = Section.COROUTINES_FLOWS,
            number = 11,
            title = "callbackFlow",
            difficulty = Difficulty.ADVANCED,
            description = "Convert a callback API to Flow",
            instructions = "Simulate a callback-based API (e.g., a timer or sensor).\nUse callbackFlow to convert it into a Flow.\nCollect the flow and display the values.\nProperly handle closing with awaitClose.",
            exerciseContent = { S06E11Exercise() },
            answerContent = { S06E11Answer() }
        ),
        Exercise(
            id = "s06_e12",
            section = Section.COROUTINES_FLOWS,
            number = 12,
            title = "Debounce Search",
            difficulty = Difficulty.ADVANCED,
            description = "Search-as-you-type with debounce",
            instructions = "Create a text field for search input.\nConvert input changes to a flow using snapshotFlow or MutableStateFlow.\nApply debounce(300ms) to avoid excessive searches.\nSimulate search results and display them.",
            exerciseContent = { S06E12Exercise() },
            answerContent = { S06E12Answer() }
        ),
        Exercise(
            id = "s06_e13",
            section = Section.COROUTINES_FLOWS,
            number = 13,
            title = "Flow Error Recovery",
            difficulty = Difficulty.ADVANCED,
            description = "catch and retry operators",
            instructions = "Create a flow that sometimes throws exceptions.\nUse the catch operator to handle errors gracefully.\nUse the retry operator to retry failed emissions.\nDisplay both the successful values and error messages.",
            exerciseContent = { S06E13Exercise() },
            answerContent = { S06E13Answer() }
        ),
        Exercise(
            id = "s06_e14",
            section = Section.COROUTINES_FLOWS,
            number = 14,
            title = "Channel Communication",
            difficulty = Difficulty.ADVANCED,
            description = "Send messages between coroutines",
            instructions = "Create a Channel for communication between a producer and consumer coroutine.\nThe producer sends numbered messages with delays.\nThe consumer receives and displays them.\nUse a Channel with a buffer.",
            exerciseContent = { S06E14Exercise() },
            answerContent = { S06E14Answer() }
        ),
        Exercise(
            id = "s06_e15",
            section = Section.COROUTINES_FLOWS,
            number = 15,
            title = "Testing Coroutines",
            difficulty = Difficulty.ADVANCED,
            description = "Demonstrate testable coroutine patterns",
            instructions = "Create a coroutine-based counter that uses a CoroutineDispatcher parameter.\nShow how injecting the dispatcher makes the code testable.\nDemonstrate running the counter with both Dispatchers.Default and a simulated test dispatcher.\nDisplay the results to show both produce the same output.",
            exerciseContent = { S06E15Exercise() },
            answerContent = { S06E15Answer() }
        )
    ))
}
