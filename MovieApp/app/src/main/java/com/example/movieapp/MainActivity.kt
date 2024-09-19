package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    App()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun App() {
    MovieAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(title = { Text(text = "Movie App") }, navigationIcon = {
                IconButton(onClick = {
                    Log.d("MovieTAG", "Navigation Icon Clicked")
                }) {
                    Icon(
                        Icons.Default.Close,
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
            Body(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    moviesList: List<String> = listOf("Movie 1", "Movie 2", "Movie 3", "Movie 4", "Movie 5")
) {
    val context = LocalContext.current

    Surface(
        modifier = modifier, color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Movies",
                style = MaterialTheme.typography.headlineLarge,
            )
            // HorizontalDivider(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
            LazyColumn {
                items(moviesList) {
                    MovieRow(it) { movieName ->
                        Log.d("MovieTAG", "Movie Row Clicked: $movieName")
                        Toast.makeText(context, movieName, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieRow(movieName: String, onMovieClick: (String) -> Unit = {}) {
    Card(modifier = Modifier
        .padding(vertical = 16.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        onClick = {
            onMovieClick.invoke(movieName)
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
                    text = movieName,
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
