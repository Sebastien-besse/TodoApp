package com.example.todoapp.Model


data class Todo(
    val content : String,
    val date: Long,
    val category: Category,
    val priority: Priority
)
