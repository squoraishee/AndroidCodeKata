package com.kata.testbed.sections.s07_dependency_injection

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s07_dependency_injection.answers.*
import com.kata.testbed.sections.s07_dependency_injection.exercises.*

fun registerDependencyInjection() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s07_e01",
            section = Section.DEPENDENCY_INJECTION,
            number = 1,
            title = "Constructor Injection",
            difficulty = Difficulty.BEGINNER,
            description = "Pass deps via constructor",
            instructions = "Create a UserService class that receives a UserRepository via constructor.\nShow how constructor injection makes dependencies explicit.",
            exerciseContent = { S07E01Exercise() },
            answerContent = { S07E01Answer() }
        ),
        Exercise(
            id = "s07_e02",
            section = Section.DEPENDENCY_INJECTION,
            number = 2,
            title = "Interface Abstraction",
            difficulty = Difficulty.BEGINNER,
            description = "Depend on interface, inject impl",
            instructions = "Define a Logger interface with a log() method.\nCreate ConsoleLogger and FileLogger implementations.\nInject Logger into a Service class and show polymorphic behavior.",
            exerciseContent = { S07E02Exercise() },
            answerContent = { S07E02Answer() }
        ),
        Exercise(
            id = "s07_e03",
            section = Section.DEPENDENCY_INJECTION,
            number = 3,
            title = "Manual DI Container",
            difficulty = Difficulty.BEGINNER,
            description = "Wire object graph in Application",
            instructions = "Create an AppContainer class that constructs and holds all dependencies.\nShow how to wire the object graph manually without a framework.",
            exerciseContent = { S07E03Exercise() },
            answerContent = { S07E03Answer() }
        ),
        Exercise(
            id = "s07_e04",
            section = Section.DEPENDENCY_INJECTION,
            number = 4,
            title = "Service Locator Refactor",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Refactor locator to proper DI",
            instructions = "Show a ServiceLocator anti-pattern where classes pull deps from a global registry.\nRefactor it to constructor injection and display the before/after difference.",
            exerciseContent = { S07E04Exercise() },
            answerContent = { S07E04Answer() }
        ),
        Exercise(
            id = "s07_e05",
            section = Section.DEPENDENCY_INJECTION,
            number = 5,
            title = "Hilt Setup",
            difficulty = Difficulty.INTERMEDIATE,
            description = "@HiltAndroidApp and first @Inject",
            instructions = "Demonstrate the Hilt setup pattern: annotated Application class, @Inject constructor.\nShow the code structure as text since we cannot use actual Hilt at runtime.",
            exerciseContent = { S07E05Exercise() },
            answerContent = { S07E05Answer() }
        ),
        Exercise(
            id = "s07_e06",
            section = Section.DEPENDENCY_INJECTION,
            number = 6,
            title = "Hilt Modules",
            difficulty = Difficulty.INTERMEDIATE,
            description = "@Provides for a dependency",
            instructions = "Show how to create a Hilt @Module with @Provides functions.\nDemonstrate providing a Retrofit instance and a Repository that depends on it.",
            exerciseContent = { S07E06Exercise() },
            answerContent = { S07E06Answer() }
        ),
        Exercise(
            id = "s07_e07",
            section = Section.DEPENDENCY_INJECTION,
            number = 7,
            title = "Hilt Binds",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Bind interface to implementation",
            instructions = "Show the @Binds pattern for mapping an interface to its implementation.\nCompare @Binds vs @Provides and when to use each.",
            exerciseContent = { S07E07Exercise() },
            answerContent = { S07E07Answer() }
        ),
        Exercise(
            id = "s07_e08",
            section = Section.DEPENDENCY_INJECTION,
            number = 8,
            title = "Scoping",
            difficulty = Difficulty.INTERMEDIATE,
            description = "@Singleton vs @ActivityScoped",
            instructions = "Demonstrate how scoping controls instance lifetime.\nShow @Singleton creating one shared instance vs unscoped creating new instances each time.",
            exerciseContent = { S07E08Exercise() },
            answerContent = { S07E08Answer() }
        ),
        Exercise(
            id = "s07_e09",
            section = Section.DEPENDENCY_INJECTION,
            number = 9,
            title = "ViewModel Injection",
            difficulty = Difficulty.ADVANCED,
            description = "@HiltViewModel with repository",
            instructions = "Show the @HiltViewModel pattern where a ViewModel receives a repository via @Inject constructor.\nDemonstrate how the ViewModel exposes state from the injected repository.",
            exerciseContent = { S07E09Exercise() },
            answerContent = { S07E09Answer() }
        ),
        Exercise(
            id = "s07_e10",
            section = Section.DEPENDENCY_INJECTION,
            number = 10,
            title = "Qualifiers",
            difficulty = Difficulty.ADVANCED,
            description = "Distinguish two implementations",
            instructions = "Create two implementations of the same interface.\nUse qualifier annotations to distinguish between them when injecting.",
            exerciseContent = { S07E10Exercise() },
            answerContent = { S07E10Answer() }
        ),
        Exercise(
            id = "s07_e11",
            section = Section.DEPENDENCY_INJECTION,
            number = 11,
            title = "Assisted Inject",
            difficulty = Difficulty.ADVANCED,
            description = "Runtime params + injected deps",
            instructions = "Show the Assisted Inject pattern where some params come from DI and others from runtime.\nDemonstrate the factory pattern that Hilt generates.",
            exerciseContent = { S07E11Exercise() },
            answerContent = { S07E11Answer() }
        ),
        Exercise(
            id = "s07_e12",
            section = Section.DEPENDENCY_INJECTION,
            number = 12,
            title = "Multi-Bindings",
            difficulty = Difficulty.ADVANCED,
            description = "Set/Map of implementations",
            instructions = "Demonstrate multi-binding where multiple implementations are collected into a Set or Map.\nShow how plugins or handlers can be registered via DI.",
            exerciseContent = { S07E12Exercise() },
            answerContent = { S07E12Answer() }
        ),
        Exercise(
            id = "s07_e13",
            section = Section.DEPENDENCY_INJECTION,
            number = 13,
            title = "Testing with Hilt",
            difficulty = Difficulty.ADVANCED,
            description = "@TestInstallIn fakes",
            instructions = "Show how to replace real dependencies with fakes in tests using @TestInstallIn.\nDemonstrate the pattern for swapping a real repository with a fake one.",
            exerciseContent = { S07E13Exercise() },
            answerContent = { S07E13Answer() }
        ),
        Exercise(
            id = "s07_e14",
            section = Section.DEPENDENCY_INJECTION,
            number = 14,
            title = "Component Dependencies",
            difficulty = Difficulty.ADVANCED,
            description = "Custom Hilt component",
            instructions = "Show how to define a custom component scope and hierarchy.\nDemonstrate when you need component dependencies beyond the standard Hilt components.",
            exerciseContent = { S07E14Exercise() },
            answerContent = { S07E14Answer() }
        ),
        Exercise(
            id = "s07_e15",
            section = Section.DEPENDENCY_INJECTION,
            number = 15,
            title = "DI Architecture Review",
            difficulty = Difficulty.ADVANCED,
            description = "Refactor tangled graph",
            instructions = "Show a tangled dependency graph with circular dependencies and hidden coupling.\nRefactor it step by step to a clean, acyclic graph with proper injection.",
            exerciseContent = { S07E15Exercise() },
            answerContent = { S07E15Answer() }
        )
    ))
}
