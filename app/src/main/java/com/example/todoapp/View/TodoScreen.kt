package com.example.todoapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.Utils.isSameDay
import com.example.todoapp.View.Components.EmptyViewComponent
import com.example.todoapp.View.Components.HorizontalCalendar
import com.example.todoapp.View.Components.TaskComponent
import com.example.todoapp.View.TodoSheet.CategoryPickerSheet
import com.example.todoapp.View.TodoSheet.DatePickerSheet
import com.example.todoapp.View.TodoSheet.PriorityPickerSheet
import com.example.todoapp.View.TodoSheet.TitleSheet
import com.example.todoapp.ViewModel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Todo(
    viewModel: TodoViewModel = viewModel(),
    onProfileClick: () -> Unit,
) {

    val state by viewModel.uiState.collectAsState()
    val selectedDate = state.calendarState.selectedDate

    val filteredTasks = state.tasks.filter { task ->
        selectedDate == null || isSameDay(task.date, selectedDate)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image profil cliquable
                Image(
                    painter = painterResource(R.drawable.profil),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable { onProfileClick() }
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.sort),
                        contentDescription = "Sort",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            val state by viewModel.uiState.collectAsState()

            HorizontalCalendar(
                calendarState = state.calendarState,
                onPreviousMonth = { viewModel.previousMonth() },
                onNextMonth = { viewModel.nextMonth() },
                onDateSelected = { viewModel.selectDate(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Liste des tâches
            if (state.tasks.isEmpty()) {
                EmptyViewComponent()
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(filteredTasks) { task ->
                        TaskComponent(
                            todo = task,
                            onCompleteClick = { viewModel.deleteTask(it) }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { viewModel.updateShowSheet(true) },
            containerColor = MaterialTheme.colorScheme.primary,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            elevation = FloatingActionButtonDefaults.elevation()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Ajouter",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(34.dp)
            )
        }
    }
    if (state.showSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            onDismissRequest = { viewModel.updateShowSheet(false) },
        ) {
            TitleSheet(viewModel = viewModel)
        }
    }
    if (state.currentStep == 2) {
        DatePickerSheet(
            onDateSelected = { millis ->
                millis?.let {
                    viewModel.onDateChange(it)
                    viewModel.nextStep()
                }
            },
            onDismiss = { viewModel.updateShowSheet(false) }
        )
    }
    if (state.currentStep == 3) {
        CategoryPickerSheet(
            uiState = state,
            onCategorySelected = { category ->
                viewModel.onCategoryChange(category)
            },
            onConfirm = { viewModel.nextStep() },
            onDismiss = { viewModel.updateShowSheet(false) }
        )
    }
    if (state.currentStep == 4) {
        PriorityPickerSheet(
            onPrioritySelected = { priority ->
                viewModel.onPriorityChange(priority)
                viewModel.saveTask()
            },
            onDismiss = { viewModel.updateShowSheet(false) }
        )
    }
}

@Preview
@Composable
private fun TodoPreview() {
    Todo(
        onProfileClick = {},
    )
}