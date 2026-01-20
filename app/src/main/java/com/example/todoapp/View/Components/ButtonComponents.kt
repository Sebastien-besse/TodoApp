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


@Composable
fun ButtonLarge(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isStroke: Boolean = false // On met false par défaut
) {
    Button(
        onClick = onClick,
        // 1. On change la couleur de fond selon isStroke
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isStroke) Color.Transparent else MaterialTheme.colorScheme.primary,
            contentColor = if (isStroke) MaterialTheme.colorScheme.primary else Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        // 2. On applique la bordure seulement si isStroke est vrai
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

/*
            // Vérification de saisie de l'email et du mot de passe avant connexion
            val isPasswordValid = auth.validatePassword(password)

            // mise à jour des états d'erreurs

            passwordError = !isPasswordValid

            // Si les prérequis des champs sont valide le bouton login est activé
            if(!uiState.isEmailError && isPasswordValid){
                auth.login(uiState.email, password)
                onLoginSuccess()
            }
 */