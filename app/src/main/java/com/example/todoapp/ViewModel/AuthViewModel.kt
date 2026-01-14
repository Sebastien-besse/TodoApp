package com.example.todoapp.ViewModel

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AuthViewModel {
    val auth = Firebase.auth


    fun validateEmail(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
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