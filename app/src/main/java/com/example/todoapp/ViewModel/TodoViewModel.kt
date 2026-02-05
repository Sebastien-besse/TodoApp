package com.example.todoapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.Model.Category
import com.example.todoapp.Model.Priority
import com.example.todoapp.Model.State.TodoUiState
import com.example.todoapp.Model.Todo
import com.example.todoapp.Model.toTodo
import com.example.todoapp.Repository.CategoryRepo
import com.example.todoapp.Repository.TaskRepo
import com.example.todoapp.dto.TaskDTO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar


class TodoViewModel(
    private val categoryRepo: CategoryRepo = CategoryRepo(),
    private val taskRepo: TaskRepo = TaskRepo()
): ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())

    val uiState: StateFlow<TodoUiState> =  _uiState


    // Autre méthode utiliser
    fun observedUiState(): StateFlow<TodoUiState> = _uiState


    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            categoryRepo.observeCategories().collect { dtos ->
                val categoryModels = dtos.map { it.toCategory() }
                _uiState.update { it.copy(categories = categoryModels) }
                fetchTasks()
            }
        }
    }

    fun fetchTasks() {
        viewModelScope.launch {
            val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

            if (currentUserId == null) {
                Log.d("TodoVM", "User not logged")
                return@launch
            }

            val dtos = taskRepo.getAllTasks(currentUserId)
            Log.d("TodoVM", "Tasks loaded: ${dtos.size}")

            val categories = _uiState.value.categories
            val todos = dtos.map { it.toTodo(categories) }

            _uiState.update { it.copy(tasks = todos) }
        }
    }
    fun onTitleChange(newTitle: String){
        _uiState.update { currentState ->
            currentState.copy(content = newTitle) }
    }

    fun onDateChange(newDate: Long) {
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





    fun resetState() {
        _uiState.update { current ->
            TodoUiState(
                categories = current.categories
            )
        }
    }


    fun saveTask() {
        val state = _uiState.value
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

        if (state.content.isNotBlank() && currentUserId != null) {
            val newTask = TaskDTO(
                content = state.content,
                date = state.date ?: 0L,
                category = state.category?.name ?: "",
                priority = state.priority?.level ?: 1,
                isCompleted = false,
                userId = currentUserId
            )

            viewModelScope.launch {
                val success = taskRepo.addTask(newTask)
                if (success) {
                    resetState()
                    updateShowSheet(false)
                    fetchTasks()
                }
            }
        }
    }


    fun deleteTask(todo: Todo) {
        viewModelScope.launch {
            val success = taskRepo.deleteTask(todo.id)
            if (success) {
                fetchTasks()
            }
        }
    }

    fun nextMonth() {
        _uiState.update { state ->
            val cs = state.calendarState
            val (newMonth, newYear) = if (cs.currentMonth == 11) 0 to cs.currentYear + 1 else cs.currentMonth + 1 to cs.currentYear
            state.copy(calendarState = cs.copy(currentMonth = newMonth, currentYear = newYear))
        }
    }

    fun previousMonth() {
        _uiState.update { state ->
            val cs = state.calendarState
            val (newMonth, newYear) = if (cs.currentMonth == 0) 11 to cs.currentYear - 1 else cs.currentMonth - 1 to cs.currentYear
            state.copy(calendarState = cs.copy(currentMonth = newMonth, currentYear = newYear))
        }
    }

    fun selectDate(dateMillis: Long) {
        _uiState.update { state ->
            state.copy(calendarState = state.calendarState.copy(selectedDate = dateMillis))
        }
    }



}