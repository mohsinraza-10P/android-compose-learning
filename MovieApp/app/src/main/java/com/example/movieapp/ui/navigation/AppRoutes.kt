package com.example.movieapp.ui.navigation

enum class AppRoutes {
    Home,
    Details;

    companion object {
        fun fromRoute(route: String?): AppRoutes {
            return when (route?.substringBefore("/")) {
                Home.name -> Home
                Details.name -> Details
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}