package com.example.quizapp.data.model

// https://raw.githubusercontent.com/itmmckernan/triviaJSON/master/world.json
class Question : ArrayList<QuestionItem>()

data class QuestionItem(
    val answer: String?,
    val category: String?,
    val choices: List<String?>?,
    val question: String?
)