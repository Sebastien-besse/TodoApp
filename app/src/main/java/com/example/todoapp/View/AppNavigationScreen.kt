package com.example.todoapp.View

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.Model.LoginRoute
import com.example.todoapp.Model.TodoRoute
import com.example.todoapp.Model.IntroRoute
import com.example.todoapp.Model.OnboardingRoute
import com.example.todoapp.Model.ProfileRoute
import com.example.todoapp.Model.RegisterRoute
import com.example.todoapp.Model.StartRoute
import kotlinx.coroutines.delay

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // Configuration de la navigation des écrans de l'application
    NavHost(navController = navController, startDestination = IntroRoute) {
        // Écran d'intro
        composable<IntroRoute> {
            IntroScreen()
            LaunchedEffect(Unit) {
                delay(1000L)
                navController.navigate(OnboardingRoute) {
                    popUpTo(IntroRoute)
                }
            }
        }

        // Écran de connexion
        composable<LoginRoute> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(TodoRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                    }
                }
            )
        }

        // Écran d'inscription
        composable<RegisterRoute> {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(TodoRoute) {
                        popUpTo(RegisterRoute) { inclusive = true }
                    }
                }
            )
        }

        // Écran de l'onboarding
        composable<OnboardingRoute> {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(StartRoute)
                }
            )
        }

        // Écran de démarrage pour choisir connexion ou inscription
        composable<StartRoute> {
            StartScreen(
                onLogin = { navController.navigate(LoginRoute) },
                onRegister = { navController.navigate(RegisterRoute) },
                onBack = { navController.navigate(OnboardingRoute) }
            )
        }

        // Écran principal Todo
        composable<TodoRoute> {
            Todo(
                onProfileClick = { navController.navigate(ProfileRoute) }
            )
        }

        // Écran Profil
        composable<ProfileRoute> {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    // Déconnexion et retour à StartScreen
                    navController.navigate(StartRoute) {
                        popUpTo(TodoRoute) { inclusive = true }
                    }
                }
            )
        }
    }
}
