package com.example.todoapp.View.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.todoapp.Utils.generateDaysForMonth
import com.example.todoapp.Utils.isSameDay
import java.time.Month
import androidx.compose.ui.unit.sp
import com.example.todoapp.Model.State.CalendarUiState

@Composable
fun HorizontalCalendar(
    calendarState: CalendarUiState,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onDateSelected: (Long) -> Unit
) {

    val days = remember(calendarState.currentMonth, calendarState.currentYear) {
        generateDaysForMonth(calendarState.currentMonth, calendarState.currentYear)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(16.dp)
    ) {

        // HEADER
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            IconButton(
                onClick = onPreviousMonth,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Default.ChevronLeft, null, tint = MaterialTheme.colorScheme.onPrimary)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = Month.of(calendarState.currentMonth + 1).name,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = calendarState.currentYear.toString(),
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                )
            }

            IconButton(
                onClick = onNextMonth,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.onPrimary)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(11.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(days) { dateMillis ->
                DayCard(
                    dateMillis = dateMillis,
                    selected = calendarState.selectedDate?.let { isSameDay(it, dateMillis) } == true,
                    onClick = onDateSelected
                )
            }
        }
    }
}


