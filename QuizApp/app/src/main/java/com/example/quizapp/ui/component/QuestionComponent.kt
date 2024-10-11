package com.example.quizapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.data.model.QuestionItem
import com.example.quizapp.ui.theme.lightBlue
import com.example.quizapp.ui.theme.lightGray
import com.example.quizapp.ui.theme.offDarkPurple
import com.example.quizapp.ui.theme.offWhite

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
            .fillMaxWidth()
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

@Composable
fun QuestionText(modifier: Modifier = Modifier, question: String?) {
    Text(
        modifier = modifier,
        text = question.toString(),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp,
        color = offWhite.copy(0.75f)
    )
}

@Composable
fun QuestionChoiceRadioButton(
    answer: MutableState<Int?>,
    correctAnswer: MutableState<Boolean?>,
    choiceIndex: Int,
    choices: List<String?>,
    question: QuestionItem?,
    choice: String?
) {
    Row(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .border(
                width = 4.dp,
                shape = RoundedCornerShape(8.dp),
                brush = Brush.linearGradient(
                    colors = listOf(
                        offDarkPurple,
                        offDarkPurple
                    )
                )
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color.Transparent)
            .clickable {
                answer.value = choiceIndex
                correctAnswer.value = choices.getOrNull(choiceIndex) == question?.answer
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors(
                selectedColor = if (correctAnswer.value == true && choiceIndex == answer.value) {
                    Color.Green.copy(alpha = 0.5f)
                } else {
                    Color.Red.copy(alpha = 0.5f)
                }
            ),
            selected = choiceIndex == answer.value,
            onClick = {
                answer.value = choiceIndex
                correctAnswer.value = choices.getOrNull(choiceIndex) == question?.answer
            }
        )
        Text(
            text = choice.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (correctAnswer.value == true && choiceIndex == answer.value) {
                Color.Green.copy(alpha = 0.5f)
            } else if (correctAnswer.value == false && choiceIndex == answer.value) {
                Color.Red.copy(alpha = 0.5f)
            } else offWhite.copy(alpha = 0.5f)
        )
    }
}

@Composable
fun CustomButton(modifier: Modifier = Modifier, buttonText: String, onClick: () -> Unit) {
    Button(
        modifier = modifier.padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
        onClick = { onClick.invoke() },
        content = {
            Text(
                text = buttonText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = offWhite
            )
        }
    )
}