package com.example.todoapp.Model.State

import com.example.todoapp.Model.Category
import com.example.todoapp.Model.Priority


data class TodoUiState(
    val content: String = "",
    val date: Long? = null,
    val category: Category? = null,
    val priority: Priority? = null,
    val currentStep: Int = 1,
    val showSheet: Boolean = false
)
