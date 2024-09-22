package com.example.movieapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.models.getMovies
import com.example.movieapp.ui.navigation.AppRoutes
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    MovieAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(
                title = { Text(text = "Movie App") },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onSecondary,
                )
            )
        }) { innerPadding ->
            Body(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                moviesList = getMovies()
            )
        }
    }
}

@Composable
private fun Body(
    navController: NavController,
    modifier: Modifier = Modifier,
    moviesList: List<Movie>,
) {
    Surface(
        modifier = modifier, color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn {
                items(moviesList) {
                    MovieRow(it) { movieId ->
                        Log.d("MovieTAG", "Movie Row Clicked: $movieId")
                        navController.navigate(route = AppRoutes.Details.name + "/$movieId")
                    }
                }
            }
        }
    }
}