package com.example.movieapp.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.models.getMovies
import com.example.movieapp.ui.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val context = LocalContext.current
    val movie = getMovies().first { movie -> movie.id == movieId }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(text = "Movie Details") }, navigationIcon = {
            IconButton(onClick = {
                Log.d("MovieTAG", "Back Icon Clicked")
                navController.popBackStack()
            }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSecondary,
        )
        )
    }) { innerPadding ->
        Body(
            modifier = Modifier.padding(innerPadding),
            movie = movie,
            context = context
        )
    }
}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    movie: Movie,
    context: Context
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            MovieRow(
                movie = movie,
                modifier = Modifier.padding(horizontal = 16.dp),
                allowExpand = true
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "${movie.title}'s Images",
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            MovieImages(movie, context)
        }
    }
}

@Composable
private fun MovieImages(
    movie: Movie,
    context: Context
) {
    LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(240.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(image).build(),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}