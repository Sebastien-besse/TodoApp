package com.example.todoapp.View

import com.example.todoapp.R
import  com.example.todoapp.View.Components.TextFieldComponent

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ViewModel.AuthViewModel
import com.example.todoapp.ui.theme.TodoAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.View.Components.ButtonLarge


@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, auth: AuthViewModel = viewModel<AuthViewModel>()) {
    var password by remember { mutableStateOf("") }

    var passwordError by remember { mutableStateOf(false) }

    val uiState by auth.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(16.dp)
                .padding(24.dp)
        ) {
            Text(
                text = stringResource(R.string.login_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(200.dp))

            // Champ de l'email
            TextFieldComponent(
                text = stringResource(R.string.login_email_label),
                value = uiState.email,
                onValueChange = { auth.updateEmail(it) },
                valueError = uiState.isEmailError,
                refreshError = {},
                errorMessage = stringResource(R.string.login_email_error),
                label = stringResource(R.string.login_email_placeholder)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Champ du mot de passe
            TextFieldComponent(
                text = stringResource(R.string.login_password_label),
                password,
                onValueChange = { password = it },
                passwordError,
                refreshError = { passwordError = false },
                stringResource(R.string.login_password_error),
                stringResource(R.string.login_password_placeholder),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(80.dp))

            ButtonLarge(
                text = stringResource(R.string.login_title),
                onClick = {
                    auth.checkEmailValidation()
                    // Vérification de saisie de l'email et du mot de passe avant connexion
                    val isPasswordValid = auth.validatePassword(password)

                    // mise à jour des états d'erreurs
                    passwordError = !isPasswordValid

                    // Si les prérequis des champs sont valide le bouton login est activé
                    if (!uiState.isEmailError && isPasswordValid) {
                        auth.login(uiState.email, password)
                        onLoginSuccess()
                    }
                })
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    TodoAppTheme {
        LoginScreen(onLoginSuccess = {})
    }
}
