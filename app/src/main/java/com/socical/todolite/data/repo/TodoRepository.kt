package com.socical.todolite.data.repo

import com.socical.todolite.domain.model.Todo
import kotlinx.coroutines.flow.StateFlow

interface TodoRepository {
    val todos: StateFlow<List<Todo>>
    suspend fun add(title: String, description: String): Long
    suspend fun update(id: Long, title: String, description: String)
    suspend fun toggle(id: Long)
    suspend fun get(id: Long): Todo?
}