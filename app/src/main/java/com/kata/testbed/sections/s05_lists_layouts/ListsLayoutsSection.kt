package com.kata.testbed.sections.s05_lists_layouts

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s05_lists_layouts.answers.*
import com.kata.testbed.sections.s05_lists_layouts.exercises.*

fun registerListsLayouts() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s05_e01",
            section = Section.LISTS_LAYOUTS,
            number = 1,
            title = "Basic LazyColumn",
            difficulty = Difficulty.BEGINNER,
            description = "Display 20 items in a vertical list",
            instructions = "Create a LazyColumn that displays 20 items.\nEach item should show \"Item #N\" where N is the index.\nUse Modifier.height(400.dp) so it works inside the dialog.",
            exerciseContent = { S05E01Exercise() },
            answerContent = { S05E01Answer() }
        ),
        Exercise(
            id = "s05_e02",
            section = Section.LISTS_LAYOUTS,
            number = 2,
            title = "Item Keys",
            difficulty = Difficulty.BEGINNER,
            description = "Stable keys for correct recomposition",
            instructions = "Create a LazyColumn with items that have stable keys.\nUse the key parameter in items() to ensure correct recomposition.\nAdd a button to shuffle the list and observe key stability.",
            exerciseContent = { S05E02Exercise() },
            answerContent = { S05E02Answer() }
        ),
        Exercise(
            id = "s05_e03",
            section = Section.LISTS_LAYOUTS,
            number = 3,
            title = "Sticky Headers",
            difficulty = Difficulty.BEGINNER,
            description = "Grouped items with sticky headers",
            instructions = "Create a LazyColumn with grouped items and sticky headers.\nGroup items by category (e.g., Fruits, Vegetables, Grains).\nUse stickyHeader { } for each group header.",
            exerciseContent = { S05E03Exercise() },
            answerContent = { S05E03Answer() }
        ),
        Exercise(
            id = "s05_e04",
            section = Section.LISTS_LAYOUTS,
            number = 4,
            title = "LazyRow",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Horizontal card carousel",
            instructions = "Create a LazyRow that displays horizontal cards.\nEach card should have a title and description.\nAdd spacing between cards and content padding.",
            exerciseContent = { S05E04Exercise() },
            answerContent = { S05E04Answer() }
        ),
        Exercise(
            id = "s05_e05",
            section = Section.LISTS_LAYOUTS,
            number = 5,
            title = "LazyVerticalGrid",
            difficulty = Difficulty.INTERMEDIATE,
            description = "2-column adaptive grid layout",
            instructions = "Create a LazyVerticalGrid with adaptive columns.\nDisplay items in a 2-column grid.\nEach item should be a card with an icon and label.",
            exerciseContent = { S05E05Exercise() },
            answerContent = { S05E05Answer() }
        ),
        Exercise(
            id = "s05_e06",
            section = Section.LISTS_LAYOUTS,
            number = 6,
            title = "Pull to Refresh",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Pull-to-refresh on a list",
            instructions = "Simulate pull-to-refresh behavior on a list.\nAdd a refresh button at the top that triggers a simulated reload.\nShow a loading indicator during refresh and update the list timestamp.",
            exerciseContent = { S05E06Exercise() },
            answerContent = { S05E06Answer() }
        ),
        Exercise(
            id = "s05_e07",
            section = Section.LISTS_LAYOUTS,
            number = 7,
            title = "Pagination",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Load more items on scroll near bottom",
            instructions = "Create a LazyColumn that loads more items as the user scrolls near the bottom.\nStart with 20 items and load 10 more each time.\nShow a loading indicator at the bottom while loading.",
            exerciseContent = { S05E07Exercise() },
            answerContent = { S05E07Answer() }
        ),
        Exercise(
            id = "s05_e08",
            section = Section.LISTS_LAYOUTS,
            number = 8,
            title = "Swipe to Dismiss",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Swipe-to-delete list items",
            instructions = "Create a list where items can be removed by swiping.\nShow a red background with a delete icon when swiping.\nRemove the item from the list after swipe completes.",
            exerciseContent = { S05E08Exercise() },
            answerContent = { S05E08Answer() }
        ),
        Exercise(
            id = "s05_e09",
            section = Section.LISTS_LAYOUTS,
            number = 9,
            title = "Drag and Reorder",
            difficulty = Difficulty.ADVANCED,
            description = "Drag-to-reorder list items",
            instructions = "Create a list where items can be reordered by long-press and drag.\nShow visual feedback during drag.\nUpdate the list order when the drag completes.",
            exerciseContent = { S05E09Exercise() },
            answerContent = { S05E09Answer() }
        ),
        Exercise(
            id = "s05_e10",
            section = Section.LISTS_LAYOUTS,
            number = 10,
            title = "Item Animations",
            difficulty = Difficulty.ADVANCED,
            description = "Animate add/remove with animateItem",
            instructions = "Create a list that animates item additions and removals.\nUse animateItem() modifier for smooth transitions.\nAdd buttons to add and remove items.",
            exerciseContent = { S05E10Exercise() },
            answerContent = { S05E10Answer() }
        ),
        Exercise(
            id = "s05_e11",
            section = Section.LISTS_LAYOUTS,
            number = 11,
            title = "Scroll State",
            difficulty = Difficulty.ADVANCED,
            description = "Track scroll position, show scroll-to-top",
            instructions = "Track the scroll position of a LazyColumn.\nShow a 'scroll to top' button when scrolled past the first item.\nDisplay the current first visible item index.",
            exerciseContent = { S05E11Exercise() },
            answerContent = { S05E11Answer() }
        ),
        Exercise(
            id = "s05_e12",
            section = Section.LISTS_LAYOUTS,
            number = 12,
            title = "Nested Scrolling",
            difficulty = Difficulty.ADVANCED,
            description = "Collapsible header with list",
            instructions = "Create a layout with a collapsible header above a LazyColumn.\nThe header should collapse as the user scrolls the list.\nUse nestedScroll or a similar approach.",
            exerciseContent = { S05E12Exercise() },
            answerContent = { S05E12Answer() }
        ),
        Exercise(
            id = "s05_e13",
            section = Section.LISTS_LAYOUTS,
            number = 13,
            title = "Staggered Grid",
            difficulty = Difficulty.ADVANCED,
            description = "Pinterest-style staggered layout",
            instructions = "Create a staggered grid layout where items have varying heights.\nUse LazyVerticalStaggeredGrid with 2 columns.\nEach item should have a random height to create the staggered effect.",
            exerciseContent = { S05E13Exercise() },
            answerContent = { S05E13Answer() }
        ),
        Exercise(
            id = "s05_e14",
            section = Section.LISTS_LAYOUTS,
            number = 14,
            title = "Content Types",
            difficulty = Difficulty.ADVANCED,
            description = "Mixed-type list optimization",
            instructions = "Create a LazyColumn with different content types (header, item, divider).\nUse the contentType parameter to optimize composition reuse.\nShow at least 3 different visual types of content.",
            exerciseContent = { S05E14Exercise() },
            answerContent = { S05E14Answer() }
        ),
        Exercise(
            id = "s05_e15",
            section = Section.LISTS_LAYOUTS,
            number = 15,
            title = "Performance Profiling",
            difficulty = Difficulty.ADVANCED,
            description = "Fix recomposition in a complex list",
            instructions = "Create a list with intentional performance issues, then fix them.\nDemonstrate the impact of stable keys, derived state, and remember.\nShow a recomposition counter to visualize the improvement.",
            exerciseContent = { S05E15Exercise() },
            answerContent = { S05E15Answer() }
        )
    ))
}
