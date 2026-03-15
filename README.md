# Android Code Kata

A hands-on Android exercise app built with Kotlin and Jetpack Compose. Each exercise gives you a skeleton composable with TODO comments — fill in the code, then check the built-in answer to compare your solution.

## What's Inside

**150 exercises** across 10 sections, progressing from beginner to advanced:

| # | Section | Topics |
|---|---------|--------|
| 01 | Kotlin Basics | val/var, null safety, sealed classes, generics, DSLs |
| 02 | Compose UI | Layouts, modifiers, text, buttons, rows/columns |
| 03 | State Management | remember, mutableStateOf, state hoisting |
| 04 | Navigation | Compose Navigation, arguments, back stack |
| 05 | Lists & Layouts | LazyColumn, LazyRow, grids, custom layouts |
| 06 | Coroutines & Flows | suspend functions, Flow, StateFlow, coroutine scopes |
| 07 | Dependency Injection | Manual DI, service locators, DI patterns |
| 08 | Room & Persistence | Entities, DAOs, database, migrations |
| 09 | Clean Architecture | Use cases, repository pattern, layer separation |
| 10 | Testing | Unit tests, Compose testing, fakes, test patterns |

Each section has 15 exercises with three difficulty levels: **Beginner**, **Intermediate**, and **Advanced**.

## How It Works

1. **Open the app** — you see a tabbed screen with all 10 sections
2. **Pick a section** — tap a tab to browse its exercises
3. **Tap "Exercise"** — opens a full-screen dialog showing the TODO instructions and the skeleton composable
4. **Write your code** — fill in the TODO items in the exercise file (e.g., `S01E01Exercise.kt`)
5. **Tap "Answer"** — opens the reference solution so you can compare

### Exercise file structure

Each exercise lives in a predictable path:

```
app/src/main/java/com/kata/testbed/sections/
  s01_kotlin_basics/
    exercises/S01E01Exercise.kt    ← Your workspace (has TODOs)
    answers/S01E01Answer.kt        ← Reference solution
  s02_compose_ui/
    exercises/S02E01Exercise.kt
    answers/S02E01Answer.kt
  ...
```

Exercises are `@Composable` functions with TODO comments telling you exactly what to build. Answers are complete, working implementations.

## Requirements

- **Android Studio** Hedgehog (2023.1) or newer
- **JDK 21**
- **Android SDK 35** (compileSdk)
- **Min SDK 26** (Android 8.0+)

## Getting Started

```bash
# Clone
git clone https://github.com/squoraishee/AndroidCodeKata.git
cd AndroidCodeKata

# Open in Android Studio
open -a "Android Studio" .

# Or build from command line
./gradlew assembleDebug
```

### Build commands

```bash
./gradlew build              # Full build
./gradlew assembleDebug      # Debug APK
./gradlew test               # Unit tests
./gradlew connectedCheck     # Instrumented tests (requires emulator/device)
./gradlew lintDebug          # Lint checks
```

## Project Structure

```
app/src/main/java/com/kata/testbed/
├── KataApp.kt                          # Application class
├── MainActivity.kt                     # Entry point — sets up tab UI
├── theme/Theme.kt                      # Material 3 theme
├── framework/
│   ├── model/
│   │   ├── Exercise.kt                 # Exercise data class (id, title, difficulty, content)
│   │   └── Section.kt                  # Section enum (10 sections)
│   ├── registry/ExerciseRegistry.kt    # Central registry for all exercises
│   └── ui/
│       ├── SectionTabScreen.kt         # Scrollable tab row for sections
│       ├── ExerciseListScreen.kt       # Card list of exercises per section
│       ├── ExerciseDialog.kt           # Full-screen dialog showing exercise
│       └── AnswerDialog.kt             # Full-screen dialog showing answer
└── sections/
    ├── SectionRegistration.kt          # Registers all 10 sections
    ├── s01_kotlin_basics/
    │   ├── KotlinBasicsSection.kt      # Registers 15 exercises for this section
    │   ├── exercises/                  # Your workspace files
    │   └── answers/                    # Reference solutions
    ├── s02_compose_ui/
    ├── ...
    └── s10_testing_advanced/
```

## Adding Your Own Exercises

1. Create a new exercise composable in the appropriate `exercises/` folder
2. Create the answer composable in the `answers/` folder
3. Register it in the section's registration file (e.g., `KotlinBasicsSection.kt`):

```kotlin
Exercise(
    id = "s01_e16",
    section = Section.KOTLIN_BASICS,
    number = 16,
    title = "Your Exercise Title",
    difficulty = Difficulty.BEGINNER,
    description = "Short description shown on the card",
    instructions = "Detailed instructions shown in the exercise dialog",
    exerciseContent = { S01E16Exercise() },
    answerContent = { S01E16Answer() }
)
```

## Tech Stack

- **Kotlin** — language
- **Jetpack Compose** — declarative UI with Material 3
- **Compose Navigation** — screen routing
- **Room** — database persistence (used in section 08 exercises)
- **Coroutines & Flow** — async and reactive patterns
- **Gradle Kotlin DSL** — build configuration

## License

MIT