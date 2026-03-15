package com.kata.testbed.sections.s09_clean_architecture

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s09_clean_architecture.answers.*
import com.kata.testbed.sections.s09_clean_architecture.exercises.*

fun registerCleanArchitecture() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s09_e01",
            section = Section.CLEAN_ARCHITECTURE,
            number = 1,
            title = "Domain Model",
            difficulty = Difficulty.BEGINNER,
            description = "Framework-free model class",
            instructions = "Create a User domain model as a plain Kotlin data class with no Android dependencies.\nDisplay an instance using Text composables.",
            exerciseContent = { S09E01Exercise() },
            answerContent = { S09E01Answer() }
        ),
        Exercise(
            id = "s09_e02",
            section = Section.CLEAN_ARCHITECTURE,
            number = 2,
            title = "Repository Interface",
            difficulty = Difficulty.BEGINNER,
            description = "Interface in domain layer",
            instructions = "Define a UserRepository interface in the domain layer with getAll, getById, save, and delete methods.\nDisplay the interface contract using Text composables.",
            exerciseContent = { S09E02Exercise() },
            answerContent = { S09E02Answer() }
        ),
        Exercise(
            id = "s09_e03",
            section = Section.CLEAN_ARCHITECTURE,
            number = 3,
            title = "Repository Implementation",
            difficulty = Difficulty.BEGINNER,
            description = "Impl in data layer",
            instructions = "Create an InMemoryUserRepository that implements the UserRepository interface.\nDemonstrate CRUD operations and display results.",
            exerciseContent = { S09E03Exercise() },
            answerContent = { S09E03Answer() }
        ),
        Exercise(
            id = "s09_e04",
            section = Section.CLEAN_ARCHITECTURE,
            number = 4,
            title = "Simple Use Case",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Single invoke operator",
            instructions = "Create a GetUsersUseCase class with an operator fun invoke() that delegates to the repository.\nDemonstrate calling the use case and display results.",
            exerciseContent = { S09E04Exercise() },
            answerContent = { S09E04Answer() }
        ),
        Exercise(
            id = "s09_e05",
            section = Section.CLEAN_ARCHITECTURE,
            number = 5,
            title = "Parameterized Use Case",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Input/output use case",
            instructions = "Create a use case that accepts input parameters and returns a typed output.\nDefine a GetUserByIdUseCase(id: String) -> User? pattern and display results.",
            exerciseContent = { S09E05Exercise() },
            answerContent = { S09E05Answer() }
        ),
        Exercise(
            id = "s09_e06",
            section = Section.CLEAN_ARCHITECTURE,
            number = 6,
            title = "Result Wrapper",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Sealed Result for success/error",
            instructions = "Create a sealed class Result<T> with Success(data: T) and Error(message: String) variants.\nUse it in a use case and display both success and error scenarios.",
            exerciseContent = { S09E06Exercise() },
            answerContent = { S09E06Answer() }
        ),
        Exercise(
            id = "s09_e07",
            section = Section.CLEAN_ARCHITECTURE,
            number = 7,
            title = "Mapper Pattern",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Entity to domain to UI model mapping",
            instructions = "Create Entity, Domain, and UiModel versions of a User.\nWrite mapper functions between each layer and display the transformations.",
            exerciseContent = { S09E07Exercise() },
            answerContent = { S09E07Answer() }
        ),
        Exercise(
            id = "s09_e08",
            section = Section.CLEAN_ARCHITECTURE,
            number = 8,
            title = "ViewModel with Use Cases",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Inject use cases, not repos",
            instructions = "Create a ViewModel-like class that depends on use cases rather than repositories directly.\nDemonstrate the separation and display the loaded state.",
            exerciseContent = { S09E08Exercise() },
            answerContent = { S09E08Answer() }
        ),
        Exercise(
            id = "s09_e09",
            section = Section.CLEAN_ARCHITECTURE,
            number = 9,
            title = "Error Propagation",
            difficulty = Difficulty.ADVANCED,
            description = "Domain errors through to UI state",
            instructions = "Define domain-specific error types (NotFound, Unauthorized, NetworkError).\nPropagate them from repository through use case to UI state and display each case.",
            exerciseContent = { S09E09Exercise() },
            answerContent = { S09E09Answer() }
        ),
        Exercise(
            id = "s09_e10",
            section = Section.CLEAN_ARCHITECTURE,
            number = 10,
            title = "Input Validation",
            difficulty = Difficulty.ADVANCED,
            description = "Validate in use case",
            instructions = "Create a CreateUserUseCase that validates input (non-empty name, valid email) before saving.\nReturn validation errors and display both valid and invalid scenarios.",
            exerciseContent = { S09E10Exercise() },
            answerContent = { S09E10Answer() }
        ),
        Exercise(
            id = "s09_e11",
            section = Section.CLEAN_ARCHITECTURE,
            number = 11,
            title = "Offline-First",
            difficulty = Difficulty.ADVANCED,
            description = "Cache first, refresh from network",
            instructions = "Simulate an offline-first pattern: read from cache, attempt network refresh, fall back to cache on failure.\nDisplay the data source and staleness status.",
            exerciseContent = { S09E11Exercise() },
            answerContent = { S09E11Answer() }
        ),
        Exercise(
            id = "s09_e12",
            section = Section.CLEAN_ARCHITECTURE,
            number = 12,
            title = "Pagination Use Case",
            difficulty = Difficulty.ADVANCED,
            description = "Paged data from use case",
            instructions = "Create a GetPagedUsersUseCase that returns pages of data with page number and page size.\nDisplay multiple pages and navigation controls.",
            exerciseContent = { S09E12Exercise() },
            answerContent = { S09E12Answer() }
        ),
        Exercise(
            id = "s09_e13",
            section = Section.CLEAN_ARCHITECTURE,
            number = 13,
            title = "Feature Module Boundary",
            difficulty = Difficulty.ADVANCED,
            description = "Self-contained feature package",
            instructions = "Structure a complete feature with its own model, repository, use case, and UI state in one package.\nDisplay the architecture as a visual dependency graph.",
            exerciseContent = { S09E13Exercise() },
            answerContent = { S09E13Answer() }
        ),
        Exercise(
            id = "s09_e14",
            section = Section.CLEAN_ARCHITECTURE,
            number = 14,
            title = "Dependency Rule",
            difficulty = Difficulty.ADVANCED,
            description = "Verify domain has no Android imports",
            instructions = "Demonstrate the dependency rule: domain layer classes must have zero Android/framework imports.\nShow a checker that validates layer boundaries and display results.",
            exerciseContent = { S09E14Exercise() },
            answerContent = { S09E14Answer() }
        ),
        Exercise(
            id = "s09_e15",
            section = Section.CLEAN_ARCHITECTURE,
            number = 15,
            title = "Full Vertical Slice",
            difficulty = Difficulty.ADVANCED,
            description = "Complete feature DB to UI",
            instructions = "Build a complete vertical slice: Entity -> DAO interface -> Repository -> Use Case -> ViewModel -> UI State.\nDisplay data flowing through all layers.",
            exerciseContent = { S09E15Exercise() },
            answerContent = { S09E15Answer() }
        )
    ))
}
