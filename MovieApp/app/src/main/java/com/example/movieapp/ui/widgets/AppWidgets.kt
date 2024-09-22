package com.example.movieapp.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.movieapp.data.models.Movie

@Composable
fun MovieRow(movie: Movie, onMovieClick: (String) -> Unit = {}) {
    val context = LocalContext.current

    Card(modifier = Modifier
        .padding(vertical = 16.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        onClick = {
            onMovieClick.invoke(movie.id)
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp),
                shape = RectangleShape
            ) {
                /*
                Icon(
                    modifier = Modifier.padding(2.dp),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Movie Image",
                    tint = MaterialTheme.colorScheme.primary
                )
                 */
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(movie.images.first())
                        .crossfade(true)
                        .transformations(RoundedCornersTransformation(
                            topLeft = 8f,
                            topRight = 8f,
                            bottomLeft = 8f,
                            bottomRight = 8f,
                        ))
                        .build(),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                )
            }
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text(
                    text = movie.title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "Director: ${movie.director}",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Released: ${movie.year}",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}