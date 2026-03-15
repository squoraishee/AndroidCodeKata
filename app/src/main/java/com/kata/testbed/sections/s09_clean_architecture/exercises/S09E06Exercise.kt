package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E06Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Result Wrapper")
        // TODO: Create a sealed class Result<out T> with:
        //   data class Success<T>(val data: T) : Result<T>()
        //   data class Error(val message: String) : Result<Nothing>()
        // TODO: Create a use case that returns Result<User>
        // TODO: Handle both Success and Error cases and display them
    }
}
