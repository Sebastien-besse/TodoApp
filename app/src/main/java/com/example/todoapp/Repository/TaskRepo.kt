package com.example.todoapp.Repository

import com.example.todoapp.dto.TaskDTO
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class TaskRepo {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun addTask(task: TaskDTO): Boolean {
        return try {
            firestore.collection("tasks")
                .add(task)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getAllTasks(userId: String): List<TaskDTO> {
        return try {
            firestore.collection("tasks")
                .whereEqualTo("userId", userId)
                .get()
                .await()
                .documents
                .mapNotNull { doc ->
                    doc.toObject(TaskDTO::class.java)?.copy(id = doc.id)
                }
        } catch (e: Exception) {
            emptyList()
        }
    }


    suspend fun deleteTask(taskId: String): Boolean {
        return try {
            firestore.collection("tasks")
                .document(taskId)
                .delete()
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

}
