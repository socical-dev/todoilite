package com.socical.todolite.data.repo

import com.socical.todolite.domain.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object InMemoryTodoRepository : TodoRepository {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    override val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private var nextId = 1L

    override suspend fun add(title: String, description: String): Long {
        val id = nextId++
        val newItem = Todo(id = id, title = title, description = description)
        _todos.update { listOf(newItem) + it }
        return id
    }

    override suspend fun update(id: Long, title: String, description: String) {
        _todos.update { list ->
            list.map { if (it.id == id) it.copy(title = title, description = description) else it }
        }
    }

    override suspend fun toggle(id: Long) {
        _todos.update { list ->
            list.map { if (it.id == id) it.copy(isDone = !it.isDone) else it }
        }
    }

    override suspend fun get(id: Long): Todo? = _todos.value.firstOrNull { it.id ==id }
}