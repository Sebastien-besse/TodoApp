package com.example.todoapp.Model.State

import java.util.Calendar

data class CalendarUiState(
    val currentMonth: Int = Calendar.getInstance().get(Calendar.MONTH),
    val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR),
    val selectedDate: Long? = null
)

