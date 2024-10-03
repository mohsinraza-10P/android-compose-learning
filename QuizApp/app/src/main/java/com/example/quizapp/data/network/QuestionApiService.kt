package com.example.quizapp.data.network

import com.example.quizapp.data.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApiService {
    @GET("world.json")
    suspend fun getAllQuestions(): Question
}