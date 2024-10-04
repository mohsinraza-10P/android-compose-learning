package com.example.quizapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.quizapp.R
import com.example.quizapp.data.model.Question
import com.example.quizapp.data.network.Response

@Composable
fun QuizScreen(modifier: Modifier, questionState: Response<Question> = Response.Loading) {
    Column(modifier = modifier) {
        AppBar()
        Body(questionState)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}

@Composable
private fun Body(questionState: Response<Question>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        QuestionsView(questionState)
    }
}

@Composable
private fun QuestionsView(questionState: Response<Question>) {
    when (questionState) {
        is Response.Loading -> {
            // Show a loading indicator
            CircularProgressIndicator()
        }

        is Response.Success -> {
            // Display the questions
            val questions = questionState.data
            LazyColumn {
                items(questions ?: emptyList()) {
                    Column {
                        Text(text = it.toString(), style = MaterialTheme.typography.bodyLarge)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }

        is Response.Error -> {
            // Show error message
            val error = questionState.exception.localizedMessage?.toString() ?: "Unknown error."
            Text(text = error, style = MaterialTheme.typography.bodyLarge, color = Color.Red)
        }
    }
}