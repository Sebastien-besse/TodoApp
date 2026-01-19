package com.example.todoapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Todo(){
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
            onClick = {},
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
    }
}

@Preview
@Composable
private fun TodoPreview(){
    Todo()
}
