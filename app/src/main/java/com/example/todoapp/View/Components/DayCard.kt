package com.example.todoapp.View.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Locale

@Composable
fun DayCard(
    dateMillis: Long,
    selected: Boolean,
    onClick: (Long) -> Unit
) {
    val cal = Calendar.getInstance().apply { timeInMillis = dateMillis }

    val dayLabel = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH)
        ?.uppercase() ?: ""

    val dayNumber = cal.get(Calendar.DAY_OF_MONTH)

    val isWeekend = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
            cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY

    Column(
        modifier = Modifier
            .width(39.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.background
            )
            .clickable { onClick(dateMillis) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = dayLabel.take(3),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isWeekend) Color.Red else MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = dayNumber.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

