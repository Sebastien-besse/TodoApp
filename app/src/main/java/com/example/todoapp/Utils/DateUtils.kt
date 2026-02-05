package com.example.todoapp.Utils

import java.util.Calendar

fun formatLongToDate(time: Long): String {
    val date = java.util.Date(time)
    val format = java.text.SimpleDateFormat("d MMM yyyy", java.util.Locale.FRANCE)
    return format.format(date)
}

fun isSameDay(date1: Long, date2: Long): Boolean {
    val cal1 = Calendar.getInstance().apply { timeInMillis = date1 }
    val cal2 = Calendar.getInstance().apply { timeInMillis = date2 }

    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

fun generateDaysForMonth(month: Int, year: Int): List<Long> {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)

    val days = mutableListOf<Long>()

    val maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    repeat(maxDays) {
        days.add(calendar.timeInMillis)
        calendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    return days
}
