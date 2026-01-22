package com.example.todoapp.View.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

// Composant de la description du champ de texte
@Composable
fun DescriptionComponent(text: String){
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White
    )
}

// Composant pour les champs email et mot de passe avec contrôle de saisie
@Composable
fun TextFieldComponent(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    valueError: Boolean,
    refreshError: ()->Unit,
    errorMessage: String,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    DescriptionComponent(text)
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
            refreshError()
        },
        isError = valueError,
        supportingText ={
            if (valueError) {
                Text(errorMessage)
            }
        } ,
        label = {
            Text(label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedLabelColor = Color.DarkGray,
            unfocusedLabelColor = Color.LightGray
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = visualTransformation
    )
}