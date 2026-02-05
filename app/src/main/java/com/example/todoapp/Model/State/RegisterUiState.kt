package com.example.todoapp.Model.State

data class RegisterUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isConfirmPasswordError: Boolean = false,
    val registerSuccess: Boolean = false
)

