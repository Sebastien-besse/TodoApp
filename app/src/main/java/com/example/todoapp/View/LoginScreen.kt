package com.example.todoapp.View

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextField
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
import com.example.todoapp.ui.theme.Background
import com.example.todoapp.ui.theme.PrimaryPurple
import com.example.todoapp.ui.theme.TodoAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val auth = AuthViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(16.dp)
                .padding(24.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(200.dp))
            // Champ de l'email
            TextFieldComponant("Username",email, onValueChange = {email = it}, emailError, refreshError = {emailError = false}, "Format de l'email invalide", "Enter your email")

            Spacer(modifier = Modifier.height(16.dp))

            // Champ du mot de passe
            TextFieldComponant("Password",password, onValueChange = {password = it}, passwordError, refreshError = {passwordError = false}, "Mot de passe trop court", "••••••••", visualTransformation = PasswordVisualTransformation())

            Spacer(modifier = Modifier.height(80.dp))

            // Bouton de connexion
            Button(
                onClick = {
                        // Vérification de saisie de l'email et du mot de passe avant connexion
                        val isEmailValid = auth.validateEmail(email)
                        val isPasswordValid = auth.validatePassword(password)

                        // mise à jour des états d'erreurs
                        emailError = !isEmailValid
                        passwordError = !isPasswordValid

                        // Si les prérequis des champs sont valide le bouton login est activé
                        if(isEmailValid && isPasswordValid){
                            auth.login(email, password)
                            onLoginSuccess()
                        }

                          },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryPurple
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(3.dp)
            ) {
                Text(text = "Login", fontSize = 16.sp)
            }
        }
    }
}

// Composant de la description du champ de texte
@Composable
fun descriptionComponant(text: String){
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White
    )
}

// Composant pour les champs email et mot de passe avec contrôle de saisie
@Composable
fun TextFieldComponant(text: String, value: String, onValueChange: (String) -> Unit, valueError: Boolean, refreshError: ()->Unit, errorMessage: String, label: String, visualTransformation: VisualTransformation = VisualTransformation.None){
    descriptionComponant(text)
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
            focusedLabelColor = Color.DarkGray
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
fun AuthPreview() {
    TodoAppTheme {
        LoginScreen(onLoginSuccess = {})
    }
}
