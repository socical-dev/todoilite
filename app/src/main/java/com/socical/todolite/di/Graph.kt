package com.socical.todolite.di

import com.socical.todolite.data.repo.InMemoryTodoRepository
import com.socical.todolite.data.repo.TodoRepository

object Graph {
    val todoRepo: TodoRepository = InMemoryTodoRepository
}