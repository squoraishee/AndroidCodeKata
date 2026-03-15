# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Kotlin + Jetpack Compose note-taking app. MVVM with Repository pattern, Room database for persistence, Material 3 UI. This project is used as a sub-agent demo — multiple Claude Code agents build different layers in parallel using git worktrees.

## Build Commands

This is a Gradle Kotlin DSL project. Once scaffolded:

```bash
./gradlew build              # Full build
./gradlew assembleDebug      # Debug APK
./gradlew test               # Unit tests (src/test/)
./gradlew connectedCheck     # Instrumented/UI tests (src/androidTest/)
./gradlew test --tests "com.demo.notes.SomeTest.specificMethod"  # Single test
./gradlew lintDebug          # Android lint
```

JDK 21 is configured as the project SDK.

## Architecture

Three-layer architecture with strict dependency direction: UI → Domain ← Data.

```
com.demo.notes.ui/        → Composables, ViewModels, UI state (sealed classes)
com.demo.notes.domain/    → Repository interfaces, use cases, domain models (no Android deps)
com.demo.notes.data/      → Repository implementations
com.demo.notes.data.local/ → Room entities, DAOs, database class
```

- **UI** depends on **domain**, never on **data** directly
- **Domain** has zero Android dependencies
- **Data** implements domain interfaces
- Core data model: `Note` with id, title, content, createdAt timestamp

## Code Style

- Composables: PascalCase, `@Preview` on every screen composable
- ViewModels: expose `StateFlow`, never `LiveData`
- Repository: interface in `domain/`, implementation in `data/`
- Room entities: data classes in `data/local/`, DAOs as interfaces
- UI state: prefer `sealed class` or `sealed interface`

## Testing

- Unit tests (`src/test/`): ViewModel and Repository tests
- UI tests (`src/androidTest/`): Compose testing with `ComposeTestRule`
- Use fakes over mocks; table-driven tests for business logic
- Test naming: `functionName_condition_expectedResult`

## Sub-Agent Coordination

This project is built using parallel sub-agents in isolated worktrees:

- **backend-developer**: Data layer (Room, Repository) — worktree
- **frontend-developer**: UI layer (Compose, ViewModel) — worktree
- **test-automater**: Tests (unit + UI) — worktree
- **code-reviewer**: Quality review — read-only, no worktree
- **refactoring-specialist**: Fix review issues — worktree
- **command-runner**: Build, run tests, gradle — no worktree
- **git-expert**: Branch management, merges — no worktree

### Orchestration Rules

1. Create a task list before launching agents
2. Use `isolation: "worktree"` when agents write code in parallel
3. Agents on **different layers** run in parallel worktrees
4. Agents on **same files** run sequentially
5. Use foreground when next step depends on agent output; background when independent
6. Launch parallel agents in a single message (multiple Task calls)
7. Use `git-expert` to merge worktree branches back; `command-runner` to verify builds

### Git Workflow

- Never push directly to main — always branch
- Branch naming: `feature/<agent-type>/<short-description>`
- Commit messages: imperative mood, include which layer changed
- Merge worktree branches via PR