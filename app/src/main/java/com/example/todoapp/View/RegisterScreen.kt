package com.example.todoapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.View.Components.TextFieldComponent
import com.example.todoapp.ViewModel.RegisterViewModel
import com.example.todoapp.View.Components.ButtonLarge


@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.registerSuccess) {
        if (uiState.registerSuccess) onRegisterSuccess()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = stringResource(R.string.register_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.height(80.dp))

            TextFieldComponent(
                text = stringResource(R.string.register_first_name_label),
                value = uiState.firstName,
                onValueChange = viewModel::updateFirstName,
                valueError = false,
                refreshError = {},
                errorMessage = "",
                label = stringResource(R.string.register_first_name_placeholder)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextFieldComponent(
                text = stringResource(R.string.register_last_name_label),
                value = uiState.lastName,
                onValueChange = viewModel::updateLastName,
                valueError = false,
                refreshError = {},
                errorMessage = "",
                label = stringResource(R.string.register_last_name_placeholder)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextFieldComponent(
                text = stringResource(R.string.register_email_label),
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                valueError = uiState.isEmailError,
                refreshError = {},
                errorMessage = stringResource(R.string.register_error_email),
                label = stringResource(R.string.register_email_placeholder)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextFieldComponent(
                text = stringResource(R.string.register_password_label),
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
                valueError = uiState.isPasswordError,
                refreshError = {},
                errorMessage = stringResource(R.string.register_error_password),
                label = stringResource(R.string.register_password_placeholder),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextFieldComponent(
                text = stringResource(R.string.register_confirm_password_label),
                value = uiState.confirmPassword,
                onValueChange = viewModel::updateConfirmPassword,
                valueError = uiState.isConfirmPasswordError,
                refreshError = {},
                errorMessage = stringResource(R.string.register_error_confirm_password),
                label = stringResource(R.string.register_password_placeholder),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(50.dp))

            ButtonLarge(
                text = stringResource(R.string.register_button),
                onClick = { viewModel.validateForm() }
            )
        }
    }
}
