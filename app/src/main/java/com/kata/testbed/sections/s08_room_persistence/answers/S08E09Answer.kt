package com.kata.testbed.sections.s08_room_persistence.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Date

private object DateConverter {
    fun fromTimestamp(value: Long): Date = Date(value)
    fun dateToTimestamp(date: Date): Long = date.time
}

private object ListConverter {
    fun fromString(value: String): List<String> =
        if (value.isEmpty()) emptyList() else value.split(",")
    fun listToString(list: List<String>): String = list.joinToString(",")
}

@Composable
fun S08E09Answer() {
    val now = Date()
    val timestamp = DateConverter.dateToTimestamp(now)
    val backToDate = DateConverter.fromTimestamp(timestamp)

    val tags = listOf("kotlin", "android", "room")
    val stored = ListConverter.listToString(tags)
    val restored = ListConverter.fromString(stored)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "TypeConverters",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Converter class pattern:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "class Converters {\n  @TypeConverter\n  fun fromTimestamp(value: Long): Date =\n    Date(value)\n\n  @TypeConverter\n  fun dateToTimestamp(date: Date): Long =\n    date.time\n\n  @TypeConverter\n  fun fromString(value: String): List<String> =\n    value.split(\",\")\n\n  @TypeConverter\n  fun listToString(list: List<String>): String =\n    list.joinToString(\",\")\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Register on Database:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "@TypeConverters(Converters::class)\nabstract class AppDatabase : RoomDatabase()",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Date conversion:", fontWeight = FontWeight.SemiBold)
        Text(text = "  Date -> Long: $timestamp")
        Text(text = "  Long -> Date: $backToDate")

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "List conversion:", fontWeight = FontWeight.SemiBold)
        Text(text = "  List -> String: \"$stored\"")
        Text(text = "  String -> List: $restored")
    }
}
