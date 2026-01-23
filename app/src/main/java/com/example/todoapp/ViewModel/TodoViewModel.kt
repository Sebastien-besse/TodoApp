package com.example.todoapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.todoapp.Model.Category
import com.example.todoapp.Model.Priority
import com.example.todoapp.Model.State.TodoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TodoViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())

    val uiState: StateFlow<TodoUiState> =  _uiState

    // Autre méthode utiliser
    fun observedUiState(): StateFlow<TodoUiState> = _uiState

    fun onTitleChange(newTitle: String){
        _uiState.update { currentState ->
            currentState.copy(content = newTitle) }
    }

    fun onDateChange(newDate: Long?) {
        _uiState.update { currentState ->
            currentState.copy(date = newDate)
        }
    }

    fun onCategoryChange(newCategory: Category){
        _uiState.update { currentState ->
            currentState.copy(category = newCategory) }
    }

    fun onPriorityChange(newPriority: Priority){
        _uiState.update { currentState ->
            currentState.copy(priority = newPriority) }
    }

    fun nextStep() {
        _uiState.update { currentState ->
            currentState.copy(currentStep = currentState.currentStep + 1, showSheet = false)

        }
    }

    fun previousStep() {
        _uiState.update { currentState ->
            currentState.copy(currentStep = currentState.currentStep - 1)
        }
    }

    fun updateShowSheet(value: Boolean){
        _uiState.update { it.copy(showSheet = value) }
    }
}