package com.example.quizapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.quizapp.R
import com.example.quizapp.data.model.Question
import com.example.quizapp.data.network.Response

@Composable
fun QuizScreen(modifier: Modifier, questionState: Response<Question> = Response.Loading) {
    var text = "";

    when (questionState) {
        is Response.Loading -> {
            // Show a loading indicator
            text = "Loading..."
        }

        is Response.Success -> {
            // Display the questions
            val questions = questionState.data
            text = questions?.joinToString("\n__________\n\n") ?: "No data."
        }

        is Response.Error -> {
            // Show error message
            val error = questionState.exception
            text = error.localizedMessage?.toString() ?: "Unknown error."
        }
    }

    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.app_name))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = text)
    }
}