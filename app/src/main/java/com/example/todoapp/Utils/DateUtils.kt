package com.example.todoapp.Utils

fun formatLongToDate(time: Long): String {
    val date = java.util.Date(time)
    val format = java.text.SimpleDateFormat("d MMM yyyy", java.util.Locale.FRANCE)
    return format.format(date)
}