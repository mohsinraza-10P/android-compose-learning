package com.example.quizapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.lightGray

@Composable
fun QuestionTracker(modifier: Modifier = Modifier, currentQuestion: Int, totalQuestions: Int) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append("Question $currentQuestion/")
            withStyle(SpanStyle(fontWeight = FontWeight.Light, fontSize = 14.sp)) {
                append("$totalQuestions")
            }
        }, color = lightGray, fontSize = 28.sp, fontWeight = FontWeight.Bold
    )
}

@Composable
fun QuestionDottedDivider(modifier: Modifier = Modifier, pathEffect: PathEffect) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .height(1.dp),
        onDraw = {
            drawLine(
                color = lightGray,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = pathEffect
            )
        }
    )
}