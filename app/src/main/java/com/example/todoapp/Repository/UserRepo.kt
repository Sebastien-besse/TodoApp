package com.example.todoapp.Repository

import com.example.todoapp.dto.UserDto
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserRepo {

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private val usersCollection = db.collection("users")

    // Crée un utilisateur dans Firestore
    fun createUser(user: UserDto, onComplete: (Boolean) -> Unit) {
        val uid = auth.currentUser?.uid ?: return onComplete(false)
        usersCollection.document(uid)
            .set(user)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    //Récupère l'utilisateur connecté depuis Firestore
    fun getCurrentUser(onResult: (UserDto?) -> Unit) {
        val uid = auth.currentUser?.uid ?: return onResult(null)
        usersCollection.document(uid)
            .get()
            .addOnSuccessListener { doc ->
                onResult(doc.toObject(UserDto::class.java))
            }
            .addOnFailureListener { onResult(null) }
    }

    // Mise à jour des champs dans Firestore
    fun updateField(field: String, value: String, onComplete: (Boolean) -> Unit) {
        val uid = auth.currentUser?.uid ?: return onComplete(false)
        usersCollection.document(uid)
            .update(field, value)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    //Mise à jour de l'email dans Firebase Authentification
    fun updateEmail(newEmail: String, onComplete: (Boolean, String?) -> Unit) {
        val user = auth.currentUser
        if (user == null) {
            onComplete(false, "Utilisateur non connecté")
            return
        }

        user.updateEmail(newEmail)
            .addOnSuccessListener { onComplete(true, null) }
            .addOnFailureListener { ex -> onComplete(false, ex.message) }
    }

    //Déconnexion
    fun logout(onComplete: () -> Unit) {
        auth.signOut()
        onComplete()
    }
}


