package com.example.quizapp.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.lightGray

@Composable
fun QuestionTracker(currentQuestion: Int, totalQuestions: Int) {
    Text(text = buildAnnotatedString {
        withStyle(
            style = ParagraphStyle(
                textIndent = TextIndent.None,
            )
        ) {
            withStyle(
                style = SpanStyle(
                    color = lightGray, fontWeight = FontWeight.Bold, fontSize = 28.sp
                )
            ) {
                append("Question $currentQuestion/")
                withStyle(
                    style = SpanStyle(
                        color = lightGray, fontWeight = FontWeight.Light, fontSize = 14.sp
                    )
                ) {
                    append("$totalQuestions")
                }
            }
        }
    })
}

@Composable
fun QuestionDottedDivider() {

}