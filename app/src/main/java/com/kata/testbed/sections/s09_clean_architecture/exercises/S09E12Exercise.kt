package com.kata.testbed.sections.s09_clean_architecture.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun S09E12Exercise() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: Pagination Use Case")
        // TODO: Create a GetPagedUsersUseCase(page: Int, pageSize: Int)
        // TODO: Return a Page<User> with items, currentPage, totalPages
        // TODO: Add buttons to navigate between pages
        // TODO: Display the current page of users
    }
}
