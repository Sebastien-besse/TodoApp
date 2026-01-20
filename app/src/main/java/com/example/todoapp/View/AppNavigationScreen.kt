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
import com.example.todoapp.Model.StartRoute
import kotlinx.coroutines.delay

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = IntroRoute) {
        // Écran d'intro
        composable<IntroRoute> {
            IntroScreen()
           LaunchedEffect(Unit) {
               delay(1000L)
               navController.navigate(OnboardingRoute){
                   popUpTo(IntroRoute)
               }

           }

        }

        // Écran de connexion
        composable<LoginRoute> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(TodoRoute)
                }
            )
        }

        // Ecran de l'onboarding
        composable<OnboardingRoute> {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(StartRoute)
                }
            )
        }

        //Ecran de démarage pour créer son compte ou se connecter
        composable<StartRoute> {
            StartScreen(
                onLogin = {
                    navController.navigate(LoginRoute)
                },
                onBack = {
                    navController.navigate(OnboardingRoute)
                }
            )
        }

        // Écran d'atterrissage
        composable<TodoRoute> {
            Todo()
        }

    }
}