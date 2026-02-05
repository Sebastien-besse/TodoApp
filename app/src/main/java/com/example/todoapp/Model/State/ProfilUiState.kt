package com.example.todoapp.Model.State

data class ProfileUiState(
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val showDialog: Boolean = false,
    val editingField: ProfileField? = null,
    val editingValue: String = ""
)

enum class ProfileField {
    FIRSTNAME, LASTNAME, EMAIL, PASSWORD
}

