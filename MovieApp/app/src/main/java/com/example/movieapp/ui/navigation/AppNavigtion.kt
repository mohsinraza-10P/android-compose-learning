package com.example.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.screens.DetailsScreen
import com.example.movieapp.ui.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Home.name,
        builder = {
            composable(route = AppRoutes.Home.name) {
                HomeScreen(navController = navController)
            }
            composable(
                route = AppRoutes.Details.name + "/{movieId}",
                arguments = listOf(
                    navArgument(name = "movieId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId")
                DetailsScreen(navController = navController, movieId = movieId)
            }
        }
    )
}