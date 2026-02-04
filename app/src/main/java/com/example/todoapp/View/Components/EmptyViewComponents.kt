package com.example.todoapp.View.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R

@Composable
fun EmptyViewComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(id = R.drawable.empty_task), // <-- ton drawable
            contentDescription = "Empty state",
            modifier = Modifier
                .width(227.dp)
                .height(227.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Texte principal
        Text(
            text = "What do you want to do today?",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Texte secondaire
        Text(
            text = "Tap + to add your tasks",
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
