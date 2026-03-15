package com.kata.testbed.sections.s08_room_persistence

import com.kata.testbed.framework.model.Difficulty
import com.kata.testbed.framework.model.Exercise
import com.kata.testbed.framework.model.Section
import com.kata.testbed.framework.registry.ExerciseRegistry
import com.kata.testbed.sections.s08_room_persistence.answers.*
import com.kata.testbed.sections.s08_room_persistence.exercises.*

fun registerRoomPersistence() {
    ExerciseRegistry.registerAll(listOf(
        Exercise(
            id = "s08_e01",
            section = Section.ROOM_PERSISTENCE,
            number = 1,
            title = "Entity Definition",
            difficulty = Difficulty.BEGINNER,
            description = "@Entity with primary key",
            instructions = "Define a Room @Entity data class with a primary key.\nShow the annotation pattern and how Room maps it to a SQLite table.",
            exerciseContent = { S08E01Exercise() },
            answerContent = { S08E01Answer() }
        ),
        Exercise(
            id = "s08_e02",
            section = Section.ROOM_PERSISTENCE,
            number = 2,
            title = "Basic DAO",
            difficulty = Difficulty.BEGINNER,
            description = "@Insert, @Query, @Delete",
            instructions = "Define a DAO interface with @Insert, @Query, and @Delete methods.\nSimulate the DAO operations with an in-memory list.",
            exerciseContent = { S08E02Exercise() },
            answerContent = { S08E02Answer() }
        ),
        Exercise(
            id = "s08_e03",
            section = Section.ROOM_PERSISTENCE,
            number = 3,
            title = "Database Setup",
            difficulty = Difficulty.BEGINNER,
            description = "RoomDatabase with databaseBuilder",
            instructions = "Show the RoomDatabase abstract class pattern with entities and DAOs.\nDemonstrate how databaseBuilder creates the database instance.",
            exerciseContent = { S08E03Exercise() },
            answerContent = { S08E03Answer() }
        ),
        Exercise(
            id = "s08_e04",
            section = Section.ROOM_PERSISTENCE,
            number = 4,
            title = "Flow Queries",
            difficulty = Difficulty.INTERMEDIATE,
            description = "DAO returns Flow for reactive updates",
            instructions = "Show how DAO methods can return Flow<List<T>> for reactive queries.\nDemonstrate how the UI observes database changes automatically.",
            exerciseContent = { S08E04Exercise() },
            answerContent = { S08E04Answer() }
        ),
        Exercise(
            id = "s08_e05",
            section = Section.ROOM_PERSISTENCE,
            number = 5,
            title = "Update and Upsert",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Conflict strategies and @Upsert",
            instructions = "Show @Update, @Upsert, and OnConflictStrategy options.\nDemonstrate how upsert inserts or updates based on primary key existence.",
            exerciseContent = { S08E05Exercise() },
            answerContent = { S08E05Answer() }
        ),
        Exercise(
            id = "s08_e06",
            section = Section.ROOM_PERSISTENCE,
            number = 6,
            title = "Parameterized Queries",
            difficulty = Difficulty.INTERMEDIATE,
            description = "WHERE and LIKE search",
            instructions = "Show @Query with :param placeholders for WHERE clauses.\nDemonstrate LIKE queries for text search functionality.",
            exerciseContent = { S08E06Exercise() },
            answerContent = { S08E06Answer() }
        ),
        Exercise(
            id = "s08_e07",
            section = Section.ROOM_PERSISTENCE,
            number = 7,
            title = "One-to-Many",
            difficulty = Difficulty.INTERMEDIATE,
            description = "@Relation parent-child",
            instructions = "Define parent and child entities with a foreign key relationship.\nUse @Relation and @Embedded to query parent with children.",
            exerciseContent = { S08E07Exercise() },
            answerContent = { S08E07Answer() }
        ),
        Exercise(
            id = "s08_e08",
            section = Section.ROOM_PERSISTENCE,
            number = 8,
            title = "Many-to-Many",
            difficulty = Difficulty.INTERMEDIATE,
            description = "Junction table for tags",
            instructions = "Create a many-to-many relationship using a junction/cross-reference table.\nShow how notes and tags relate through a NoteTagCrossRef entity.",
            exerciseContent = { S08E08Exercise() },
            answerContent = { S08E08Answer() }
        ),
        Exercise(
            id = "s08_e09",
            section = Section.ROOM_PERSISTENCE,
            number = 9,
            title = "TypeConverters",
            difficulty = Difficulty.ADVANCED,
            description = "Store Date and List types",
            instructions = "Create @TypeConverter functions to convert Date and List types to/from storable formats.\nShow how Room uses these converters transparently.",
            exerciseContent = { S08E09Exercise() },
            answerContent = { S08E09Answer() }
        ),
        Exercise(
            id = "s08_e10",
            section = Section.ROOM_PERSISTENCE,
            number = 10,
            title = "Migration",
            difficulty = Difficulty.ADVANCED,
            description = "Add column migration",
            instructions = "Show how to write a Migration that adds a column to an existing table.\nDemonstrate the migration object with SQL ALTER TABLE statement.",
            exerciseContent = { S08E10Exercise() },
            answerContent = { S08E10Answer() }
        ),
        Exercise(
            id = "s08_e11",
            section = Section.ROOM_PERSISTENCE,
            number = 11,
            title = "Auto Migration",
            difficulty = Difficulty.ADVANCED,
            description = "Simple schema auto-migration",
            instructions = "Show how Room auto-migration handles simple schema changes.\nDemonstrate @AutoMigration annotation and AutoMigrationSpec for renames/deletes.",
            exerciseContent = { S08E11Exercise() },
            answerContent = { S08E11Answer() }
        ),
        Exercise(
            id = "s08_e12",
            section = Section.ROOM_PERSISTENCE,
            number = 12,
            title = "Transactions",
            difficulty = Difficulty.ADVANCED,
            description = "@Transaction for grouped operations",
            instructions = "Show how @Transaction ensures multiple operations succeed or fail together.\nDemonstrate a DAO method that inserts a parent and its children atomically.",
            exerciseContent = { S08E12Exercise() },
            answerContent = { S08E12Answer() }
        ),
        Exercise(
            id = "s08_e13",
            section = Section.ROOM_PERSISTENCE,
            number = 13,
            title = "Prepopulate",
            difficulty = Difficulty.ADVANCED,
            description = "createFromAsset pre-filled DB",
            instructions = "Show how to prepopulate a Room database from an asset file.\nDemonstrate createFromAsset and createFromFile builder methods.",
            exerciseContent = { S08E13Exercise() },
            answerContent = { S08E13Answer() }
        ),
        Exercise(
            id = "s08_e14",
            section = Section.ROOM_PERSISTENCE,
            number = 14,
            title = "Full-Text Search",
            difficulty = Difficulty.ADVANCED,
            description = "FTS4 for fast search",
            instructions = "Show the @Fts4 annotation for full-text search capabilities.\nDemonstrate how FTS entities enable fast MATCH queries on text content.",
            exerciseContent = { S08E14Exercise() },
            answerContent = { S08E14Answer() }
        ),
        Exercise(
            id = "s08_e15",
            section = Section.ROOM_PERSISTENCE,
            number = 15,
            title = "Database Testing",
            difficulty = Difficulty.ADVANCED,
            description = "In-memory DB instrumented tests",
            instructions = "Show how to test Room DAOs using an in-memory database.\nDemonstrate the test setup pattern with inMemoryDatabaseBuilder.",
            exerciseContent = { S08E15Exercise() },
            answerContent = { S08E15Answer() }
        )
    ))
}
