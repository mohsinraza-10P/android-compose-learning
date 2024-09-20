package com.example.movieapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.ui.navigation.AppRoutes
import com.example.movieapp.ui.theme.MovieAppTheme

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
            Body(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun Body(
    navController: NavController,
    modifier: Modifier = Modifier,
    moviesList: List<String> = listOf(
        "Avatar",
        "300",
        "Harry Potter",
        "Happiness...",
        "Cross the Line...",
        "Be Happy...",
        "Happy Feet...",
        "Life"
    ),
) {
    Surface(
        modifier = modifier, color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn {
                items(moviesList) {
                    MovieRow(it) { movie ->
                        Log.d("MovieTAG", "Movie Row Clicked: $movie")
                        navController.navigate(route = AppRoutes.Details.name + "/$movie")
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieRow(movie: String, onMovieClick: (String) -> Unit = {}) {
    Card(modifier = Modifier
        .padding(vertical = 16.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        onClick = {
            onMovieClick.invoke(movie)
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier
                    .size(72.dp)
                    .padding(8.dp), shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier.padding(2.dp),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Movie Image",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text(
                    text = movie,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Movie Description",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}