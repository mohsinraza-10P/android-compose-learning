package com.example.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.screens.DetailScreen
import com.example.movieapp.ui.screens.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieRoutes.Home.name,
        builder = {
            composable(MovieRoutes.Home.name) {
                HomeScreen(navController = navController)
            }
            composable(MovieRoutes.Detail.name) {
                DetailScreen(navController = navController)
            }
        }
    )
}