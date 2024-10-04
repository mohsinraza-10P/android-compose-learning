package com.example.quizapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.R
import com.example.quizapp.data.model.Question
import com.example.quizapp.data.network.Response
import com.example.quizapp.ui.theme.QuizAppTheme
import com.example.quizapp.ui.view_model.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            QuizAppTheme {
                App { innerPadding ->
                    val questionVM: QuestionViewModel by viewModels()
                    val questionsState = questionVM.questionsState.collectAsState().value
                    QuizHome(
                        modifier = Modifier.padding(innerPadding),
                        questionsState = questionsState
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    App { innerPadding ->
        QuizHome(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun App(content: @Composable (PaddingValues) -> Unit) {
    QuizAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            content(innerPadding)
        }
    }
}

@Composable
fun QuizHome(modifier: Modifier, questionsState: Response<Question> = Response.Loading) {
    var text = "";

    when (questionsState) {
        is Response.Loading -> {
            // Show a loading indicator
            text = "Loading..."
        }

        is Response.Success -> {
            // Display the questions
            val questions = questionsState.data
            text = questions?.joinToString("\n__________\n\n") ?: "No data."
        }

        is Response.Error -> {
            // Show error message
            val error = questionsState.exception
            text = error.localizedMessage?.toString() ?: "Unknown error."
        }
    }

    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.app_name))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = text)
    }
}