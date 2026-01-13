package com.example.todoapp.Model

import java.util.Date

data class Todo(
    val content : String,
    val date: Date,
    val category: Category,
    val priority: Priority
)
