package com.example.todoapp.View.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Composant bouton qui prend une fonction en paramètre et choix du style du bouton, soit en stroke ou plein
@Composable
fun ButtonLarge(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isStroke: Boolean = false,

) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isStroke) Color.Transparent else MaterialTheme.colorScheme.primary,
            contentColor = if (isStroke) MaterialTheme.colorScheme.primary else Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        border = if (isStroke) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        shape = RoundedCornerShape(4.dp),

    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}