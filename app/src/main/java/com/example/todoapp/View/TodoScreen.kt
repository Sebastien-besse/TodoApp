package com.example.todoapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.View.TodoSheet.DatePickerSheet
import com.example.todoapp.View.TodoSheet.TitleSheet
import com.example.todoapp.ViewModel.TodoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Todo(viewModel: TodoViewModel = viewModel()){
    // On récupère l'état actuel depuis le ViewModel
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Text(
            "Todo Sceen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White

        )
        //Bouton ajouter
        Button(
            onClick = {viewModel.updateShowSheet(true)},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomEnd),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),

            ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Ajouter",
                modifier = Modifier.size(34.dp)
            )
        }

        if (state.showSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                onDismissRequest = { viewModel.updateShowSheet(false) },
            ) {
                TitleSheet(
                    viewModel = viewModel,

                )
            }
        }
        if (state.currentStep == 2) {
            DatePickerSheet(
                onDateSelected = { millis ->
                    // 1. On enregistre la date dans le ViewModel
                    viewModel.onDateChange(millis)

                    // 2. On passe à l'étape suivante (la priorité)
                    viewModel.nextStep()

                },
                onDismiss = { /* Optionnel : revenir à l'étape 1 ou fermer */ }
            )
        }
    }
}

@Preview
@Composable
private fun TodoPreview(){
    Todo()
}
