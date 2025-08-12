package com.socical.todolite.domain.model

data class Todo(
    val id: Long,
    val title: String,
    val description: String? = null,
    val isDone: Boolean = false,
    val dueAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)