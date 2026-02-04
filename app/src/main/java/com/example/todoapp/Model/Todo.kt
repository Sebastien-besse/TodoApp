package com.example.todoapp.Model

import com.example.todoapp.dto.TaskDTO


data class Todo(
    val id: String,
    val content : String,
    val date: Long,
    val category: Category,
    val priority: Priority

)
fun TaskDTO.toTodo(allCategories: List<Category>): Todo {
    val foundCategory = allCategories.find { it.name == this.category }
        ?: allCategories.first()

    val foundPriority = Priority.fromLevel(this.priority)

    return Todo(
        id = this.id,
        content = this.content,
        date = this.date,
        category = foundCategory,
        priority = foundPriority
    )
}
