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
    Text(
        text = buildAnnotatedString {
            append("Question $currentQuestion/")
            withStyle(SpanStyle(fontWeight = FontWeight.Light, fontSize = 14.sp)) {
                append("$totalQuestions")
            }
        }, color = lightGray, fontSize = 28.sp, fontWeight = FontWeight.Bold
    )
}

@Composable
fun QuestionDottedDivider() {

}