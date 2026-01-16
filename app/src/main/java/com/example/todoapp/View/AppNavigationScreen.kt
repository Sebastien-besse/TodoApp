package com.example.todoapp.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.Model.LoginRoute
import com.example.todoapp.Model.TodoRoute


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginRoute) {
        // Écran de connexion
        composable<LoginRoute> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(TodoRoute)
                }
            )
        }
        // Écran d'atterrissage
        composable<TodoRoute> {
            Todo()
        }
    }
}