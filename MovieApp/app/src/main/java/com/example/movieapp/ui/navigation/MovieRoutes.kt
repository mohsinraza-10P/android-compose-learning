package com.example.movieapp.ui.navigation

enum class MovieRoutes {
    Home,
    Detail;

    companion object {
        fun fromRoute(route: String?) : MovieRoutes {
            return when(route?.substringBefore("/")) {
                Home.name -> Home
                Detail.name -> Detail
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }

}