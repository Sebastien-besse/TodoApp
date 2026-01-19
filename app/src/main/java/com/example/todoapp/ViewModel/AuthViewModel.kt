package com.example.todoapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todoapp.Model.State.LoginUiState
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> =  _uiState.asStateFlow()
    val auth = Firebase.auth

    fun validateEmail(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun updateEmail(newEmail: String){
        _uiState.update { it.copy(email = newEmail, isEmailError = false) }
    }

    fun checkEmailValidation() {
        val isValid = validateEmail(_uiState.value.email)
        _uiState.update { it.copy(isEmailError = !isValid) }
    }
    fun validatePassword(password: String): Boolean{
        return password.length > 6
    }

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    Log.d("Auth","Bienvenue: ${user?.email}")
                }else{
                    Log.d("Auth","Mot de passe ou email invalid : ${task.exception?.message}")
                }
            }
    }
}