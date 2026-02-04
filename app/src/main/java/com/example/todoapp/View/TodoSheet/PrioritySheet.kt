package com.example.todoapp.View.TodoSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.Model.Priority
import com.example.todoapp.R
import com.example.todoapp.View.Components.ButtonLarge
import com.example.todoapp.View.Components.SelectPriorityComponent

@Composable
fun PriorityPickerSheet(
    onPrioritySelected: (Priority) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedPriority by remember { mutableStateOf<Priority?>(null) }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.tertiary,

        onDismissRequest = onDismiss,

        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.priority_title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
        },

        text = {
            Column {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .height(2.dp)
                        .clip(RoundedCornerShape(50)),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(Priority.entries) { priority ->
                        SelectPriorityComponent(
                            value = priority.level.toString(),
                            isSelected = selectedPriority == priority,
                            onClick = { selectedPriority = priority }
                        )
                    }
                }
            }
        }
        ,
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Cancel Button
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.priority_button_cancel),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Save Button
                ButtonLarge(
                    text = stringResource(R.string.priority_button_save),
                    onClick = {
                        selectedPriority?.let {
                            onPrioritySelected(it)
                            onDismiss()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    isStroke = false
                )
            }
        }
    )
}