package com.example.doancoso.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doancoso.data.repository.AuthService
import com.google.android.play.core.integrity.au

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Signup : Screen("signup")
    data object Home : Screen("home")
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController(), authService: AuthService) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController,authService)
        }
        composable(Screen.Signup.route) {
            SignupScreen(navController,authService)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
    }
}
