package com.example.quizapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.data.model.Question
import com.example.quizapp.data.model.QuestionItem
import com.example.quizapp.data.network.Response
import com.example.quizapp.ui.screen.QuizContent
import com.example.quizapp.ui.screen.QuizScreen
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
                    val viewModel: QuestionViewModel by viewModels()
                    QuizScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
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
        val questionItem = QuestionItem(
            answer = "Mohsin",
            category = null,
            choices = listOf("Mohsin", "Syed", "Raza"),
            question = "What is your name?"
        )
        val question = Question().apply { add(questionItem) }
        val response = Response.Success(question)
        QuizContent(
            modifier = Modifier.padding(innerPadding),
            response = response
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