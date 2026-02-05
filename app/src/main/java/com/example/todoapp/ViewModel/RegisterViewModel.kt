package com.example.todoapp.ViewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.todoapp.Model.State.RegisterUiState
import com.example.todoapp.Repository.UserRepo
import com.example.todoapp.dto.UserDto
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val repo: UserRepo = UserRepo()
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val auth = Firebase.auth

    fun updateFirstName(value: String) {
        _uiState.update { it.copy(firstName = value) }
    }
    fun updateLastName(value: String) {
        _uiState.update { it.copy(lastName = value) }
    }
    fun updateEmail(value: String) {
        _uiState.update { it.copy(email = value, isEmailError = false) }
    }
    fun updatePassword(value: String) {
        _uiState.update { it.copy(password = value, isPasswordError = false) }
    }
    fun updateConfirmPassword(value: String) {
        _uiState.update { it.copy(confirmPassword = value, isConfirmPasswordError = false) }
    }

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun validatePassword(password: String) = password.length > 6

    fun validateForm() {
        val state = _uiState.value
        val emailError = !validateEmail(state.email)
        val passwordError = !validatePassword(state.password)
        val confirmError = state.password != state.confirmPassword

        _uiState.update {
            it.copy(
                isEmailError = emailError,
                isPasswordError = passwordError,
                isConfirmPasswordError = confirmError
            )
        }

        if (!emailError && !passwordError && !confirmError) {
            register(state.firstName, state.lastName, state.email, state.password)
        }
    }

    private fun register(firstName: String, lastName: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser ?: return@addOnCompleteListener
                    val userDto = UserDto(
                        uid = user.uid,
                        firstName = firstName,
                        lastName = lastName,
                        email = email
                    )
                    repo.createUser(userDto) { success ->
                        _uiState.update { it.copy(registerSuccess = success) }
                    }
                } else {
                    _uiState.update { it.copy(registerSuccess = false) }
                }
            }
    }
}

