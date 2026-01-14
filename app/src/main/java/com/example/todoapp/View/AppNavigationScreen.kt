package com.example.todoapp.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        // Écran de connexion
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("todo")
                }
            )
        }
        // Écran d'atterrissage
        composable("todo") {
            todo()
        }
    }
}