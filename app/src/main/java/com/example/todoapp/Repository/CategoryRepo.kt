package com.example.todoapp.Repository

import android.util.Log
import com.example.todoapp.dto.CategoryDTO
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CategoryRepo {
    private val firestore = FirebaseFirestore.getInstance()

    fun observeCategories(): Flow<List<CategoryDTO>> = callbackFlow {
        val listener = firestore.collection("categories")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val categoryDtos = snapshot?.documents?.mapNotNull { doc ->
                    try {
                        val image = doc.getString("image")
                        val name = doc.getString("name")

                        // ✅ SAFE COLOR READ (string / number / map / null)
                        val rawColor = doc.get("color")
                        val color = when (rawColor) {
                            is String -> rawColor
                            is Number -> rawColor.toString()
                            else -> null
                        }

                        if (image != null && name != null && color != null) {
                            CategoryDTO(image, name, color)
                        } else null

                    } catch (e: Exception) {
                        Log.e("CategoryRepo", "Bad category doc: ${doc.id}", e)
                        null
                    }
                } ?: emptyList()

                trySend(categoryDtos).isSuccess
            }

        awaitClose { listener.remove() }
    }
}

