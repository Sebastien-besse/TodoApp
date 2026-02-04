package com.example.todoapp.dto

import androidx.compose.ui.graphics.Color
import com.example.todoapp.Model.Category
import com.example.todoapp.Model.Priority
import com.example.todoapp.Model.Todo
import com.example.todoapp.R

data class TaskDTO(
    val id: String = "",
    val content: String = "",
    val date: Long = 0L,
    val category: String = "",
    val priority: Int = 1,
    var isCompleted: Boolean = false,
    val userId: String = ""
) {
    fun TaskDTO.toTodo(categories: List<Category>): Todo {
        val foundCategory = categories.find { it.name == category }
            ?: Category(R.drawable.grocery, "Default", Color.Gray)

        return Todo(
            id = id,
            content = content,
            date = date,
            category = foundCategory,
            priority = Priority.fromLevel(priority)
        )
    }
}



