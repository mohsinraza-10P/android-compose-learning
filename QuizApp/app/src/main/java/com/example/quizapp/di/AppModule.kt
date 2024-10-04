package com.example.quizapp.di

import com.example.quizapp.data.network.QuestionApiService
import com.example.quizapp.data.repository.QuestionRepository
import com.example.quizapp.data.repository.QuestionRepositoryImpl
import com.example.quizapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuestionApiService(retrofit: Retrofit): QuestionApiService {
        return retrofit.create(QuestionApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideQuestionRepository(apiService: QuestionApiService): QuestionRepository {
        return QuestionRepositoryImpl(apiService)
    }
}