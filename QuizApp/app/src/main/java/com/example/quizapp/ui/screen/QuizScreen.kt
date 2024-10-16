package com.example.quizapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizapp.R
import com.example.quizapp.data.model.Question
import com.example.quizapp.data.model.QuestionItem
import com.example.quizapp.data.network.Response
import com.example.quizapp.ui.component.CustomButton
import com.example.quizapp.ui.component.QuestionChoiceRadioButton
import com.example.quizapp.ui.component.QuestionDottedDivider
import com.example.quizapp.ui.component.QuestionText
import com.example.quizapp.ui.component.QuestionTracker
import com.example.quizapp.ui.theme.darkPurple
import com.example.quizapp.ui.theme.lightPurple
import com.example.quizapp.ui.view_model.QuestionViewModel

@Composable
fun QuizScreen(modifier: Modifier, viewModel: QuestionViewModel = hiltViewModel()) {
    val response = viewModel.questionState.collectAsState().value
    QuizContent(modifier, response)

}

@Composable
fun QuizContent(modifier: Modifier = Modifier, response: Response<Question>) {
    Column(modifier = modifier) {
        AppBar()
        Body(response)
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
            .copy(containerColor = lightPurple, titleContentColor = Color.White)
    )
}

@Composable
private fun Body(response: Response<Question>) {
    when (response) {
        is Response.Loading -> {
            // Show a loading indicator
            LoaderView()
        }

        is Response.Success -> {
            // Display the questions
            val questions = response.data
            if (questions.isNullOrEmpty()) {
                EmptyView()
            } else {
                QuestionView(questions.toMutableList())
            }
        }

        is Response.Error -> {
            // Show error message
            val error = response.exception.localizedMessage?.toString() ?: "Unknown error."
            ErrorView(error)
        }
    }
}

@Composable
private fun LoaderView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun QuestionView(questions: MutableList<QuestionItem>) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val questionIndex = remember {
        mutableIntStateOf(0)
    }
    val question = questions.getOrNull(questionIndex.intValue)
    val choices = remember(question) {
        question?.choices?.toMutableList() ?: emptyList()
    }
    val answer = remember(question) {
        mutableStateOf<Int?>(null)
    }
    val correctAnswer = remember(question) {
        mutableStateOf<Boolean?>(null)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkPurple
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            QuestionTracker(
                currentQuestion = questionIndex.intValue + 1,
                totalQuestions = questions.size
            )
            QuestionDottedDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                pathEffect = pathEffect
            )
            QuestionText(
                modifier = Modifier.fillMaxHeight(fraction = 0.3f),
                question = question?.question
            )
            LazyColumn {
                itemsIndexed(choices) { index, choice ->
                    QuestionChoiceRadioButton(
                        answer,
                        correctAnswer,
                        index,
                        choices,
                        question,
                        choice
                    )
                }
            }
            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                buttonText = if (questionIndex.intValue == questions.size - 1) "Done" else "Next",
                onClick = {
                    if (questionIndex.intValue < questions.size - 1) {
                        questionIndex.intValue += 1
                    } else {
                        Log.d("QuizTAG", "No more questions.")
                    }
                }
            )
        }
    }
}

@Composable
private fun ErrorView(error: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = error,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Red
        )
    }
}

@Composable
private fun EmptyView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "No data found.",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}