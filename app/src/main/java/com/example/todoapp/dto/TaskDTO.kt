package com.example.todoapp.dto

// Utiliser dans pour les appels
data class TaskDTO(
    val id: String,
    val title: String,
    val type: String,
    val firstname: String,
    val lastname: String
)

// Utiliser dans les view
data class Task(
    val id: String,
    val title: String,
    val type: String,
    val username: String
)

// Convertir le DTO au Model
fun TaskDTO.toTask(): Task{
    return Task(
        id = id,
        title = title,
        type = type,
        username = "$firstname $lastname"
    )
}
