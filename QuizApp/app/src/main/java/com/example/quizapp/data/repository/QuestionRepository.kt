package com.example.quizapp.data.repository

import com.example.quizapp.data.model.Question
import com.example.quizapp.data.network.QuestionApiService
import com.example.quizapp.data.network.Response
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getAllQuestions(): Response<Question>
}

class QuestionRepositoryImpl @Inject constructor(
    private val apiService: QuestionApiService
) : QuestionRepository {

    override suspend fun getAllQuestions(): Response<Question> {
        return try {
            val response = apiService.getAllQuestions()
            Response.Success(response)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}