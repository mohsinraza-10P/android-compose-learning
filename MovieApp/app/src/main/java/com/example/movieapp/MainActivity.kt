package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
         Scaffold(
             modifier = Modifier.fillMaxSize(),
             topBar = {
                 TopAppBar(
                     title = { Text(text = "Movie App") },
                     navigationIcon = {
                         IconButton(
                             onClick = {
                                 Log.d("MovieTAG", "Navigation Icon Clicked")
                             }
                         ) {
                             Icon(Icons.Default.Close, contentDescription = null)
                         }
                     },
                     colors = TopAppBarDefaults.topAppBarColors().copy(
                         containerColor = MaterialTheme.colorScheme.primaryContainer,
                         titleContentColor = MaterialTheme.colorScheme.onBackground,
                     )
                 )
             }
         ) { innerPadding ->
            Body(modifier = Modifier.padding(innerPadding))
         }
    }
}

@Composable
private fun Body(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
    }
}
