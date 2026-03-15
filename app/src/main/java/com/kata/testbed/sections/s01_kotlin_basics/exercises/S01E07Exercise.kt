package com.kata.testbed.sections.s01_kotlin_basics.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S01E07Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Define a sealed class `Result` with:
        //   data class Success(val data: String) : Result()
        //   data class Error(val message: String) : Result()
        //   object Loading : Result()
        // TODO: Create instances of Success, Error, and Loading
        // TODO: Write a when expression that exhaustively handles each case
        // TODO: Display the result of each case using Text composables
    }
}
