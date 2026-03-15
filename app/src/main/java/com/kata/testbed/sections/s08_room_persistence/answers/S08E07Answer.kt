package com.kata.testbed.sections.s08_room_persistence.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class Author(val authorId: Int, val name: String)
private data class Book(val bookId: Int, val title: String, val authorId: Int)
private data class AuthorWithBooks(val author: Author, val books: List<Book>)

@Composable
fun S08E07Answer() {
    val authors = listOf(
        Author(1, "J.K. Rowling"),
        Author(2, "George Orwell")
    )
    val books = listOf(
        Book(1, "Harry Potter", 1),
        Book(2, "Fantastic Beasts", 1),
        Book(3, "1984", 2),
        Book(4, "Animal Farm", 2)
    )
    val authorsWithBooks = authors.map { author ->
        AuthorWithBooks(author, books.filter { it.authorId == author.authorId })
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "One-to-Many Relationship",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Room @Relation pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@Entity\ndata class Author(\n  @PrimaryKey val authorId: Int,\n  val name: String\n)\n\n@Entity(foreignKeys = [ForeignKey(\n  entity = Author::class,\n  parentColumns = [\"authorId\"],\n  childColumns = [\"authorId\"]\n)])\ndata class Book(\n  @PrimaryKey val bookId: Int,\n  val title: String,\n  val authorId: Int\n)\n\ndata class AuthorWithBooks(\n  @Embedded val author: Author,\n  @Relation(\n    parentColumn = \"authorId\",\n    entityColumn = \"authorId\"\n  )\n  val books: List<Book>\n)",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Query results:", fontWeight = FontWeight.SemiBold)
        authorsWithBooks.forEach { awb ->
            Text(text = "  ${awb.author.name}:", fontWeight = FontWeight.SemiBold)
            awb.books.forEach { book ->
                Text(text = "    - ${book.title}")
            }
        }
    }
}
