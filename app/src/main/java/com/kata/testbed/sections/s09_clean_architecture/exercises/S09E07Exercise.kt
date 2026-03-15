package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E07Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Mapper Pattern")
        // TODO: Create UserEntity (data layer), User (domain), UserUiModel (UI layer)
        // TODO: Write UserEntity.toDomain(): User mapper
        // TODO: Write User.toUiModel(): UserUiModel mapper
        // TODO: Show data flowing through all three representations
    }
}
