package com.example.todoapp.ViewModel

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AuthViewModel {
    val auth = Firebase.auth

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