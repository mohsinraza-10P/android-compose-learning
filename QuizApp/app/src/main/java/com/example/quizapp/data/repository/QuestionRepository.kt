package com.example.quizapp.data.repository

import com.example.quizapp.data.model.Question
import com.example.quizapp.data.network.QuestionApiService
import com.example.quizapp.data.network.Response
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getAllQuestions(): Flow<Response<Question>>
}

class QuestionRepositoryImpl @Inject constructor(
    private val apiService: QuestionApiService
) : QuestionRepository {

    override suspend fun getAllQuestions(): Flow<Response<Question>> = flow {
        emit(Response.Loading)
        try {
            val data = apiService.getAllQuestions()
            emit(Response.Success(data))
        } catch (e: Exception) {
            emit(Response.Error(e))
        }
    }
}