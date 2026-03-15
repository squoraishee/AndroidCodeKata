package com.kata.testbed.sections.s09_clean_architecture.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class E08User(val id: String, val name: String, val email: String)

interface E08UserRepository {
    fun getAll(): List<E08User>
    fun getById(id: String): E08User?
}

class E08InMemoryUserRepository : E08UserRepository {
    private val users = listOf(
        E08User("1", "Alice", "alice@example.com"),
        E08User("2", "Bob", "bob@example.com")
    )
    override fun getAll(): List<E08User> = users
    override fun getById(id: String): E08User? = users.find { it.id == id }
}

class E08GetUsersUseCase(private val repo: E08UserRepository) {
    operator fun invoke(): List<E08User> = repo.getAll()
}

class E08GetUserByIdUseCase(private val repo: E08UserRepository) {
    operator fun invoke(id: String): E08User? = repo.getById(id)
}

sealed interface E08UserListState {
    data object Loading : E08UserListState
    data class Success(val users: List<E08User>) : E08UserListState
    data class Error(val message: String) : E08UserListState
}

class E08UserListViewModel(
    private val getUsers: E08GetUsersUseCase,
    private val getUserById: E08GetUserByIdUseCase
) {
    var state: E08UserListState = E08UserListState.Loading
        private set

    fun loadUsers() {
        state = try {
            val users = getUsers()
            E08UserListState.Success(users)
        } catch (e: Exception) {
            E08UserListState.Error(e.message ?: "Unknown error")
        }
    }

    fun findUser(id: String): E08User? = getUserById(id)
}

@Composable
fun S09E08Answer() {
    val repo = E08InMemoryUserRepository()
    val viewModel = E08UserListViewModel(
        getUsers = E08GetUsersUseCase(repo),
        getUserById = E08GetUserByIdUseCase(repo)
    )
    viewModel.loadUsers()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("ViewModel with Use Cases", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("ViewModel depends on use cases, never on repositories directly.")

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("ViewModel constructor:", fontWeight = FontWeight.Bold)
        Text("  getUsers: GetUsersUseCase")
        Text("  getUserById: GetUserByIdUseCase")

        Spacer(modifier = Modifier.height(8.dp))
        Text("State after loadUsers():", fontWeight = FontWeight.Bold)
        when (val state = viewModel.state) {
            is E08UserListState.Loading -> Text("  Loading...")
            is E08UserListState.Success -> {
                state.users.forEach { Text("  ${it.name} - ${it.email}") }
            }
            is E08UserListState.Error -> Text("  Error: ${state.message}")
        }

        Spacer(modifier = Modifier.height(8.dp))
        val found = viewModel.findUser("1")
        Text("findUser(\"1\"): ${found?.name}", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Key: Each use case is a single responsibility. The ViewModel composes them. " +
                "Testing is easy because use cases can be faked independently.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
