package com.example.quizapp.ui.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.model.Question
import com.example.quizapp.data.network.Response
import com.example.quizapp.data.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {

    private var _questionState = MutableStateFlow<Response<Question>>(Response.Loading)
    val questionState = _questionState.asStateFlow()

    init {
        getAllQuestions()
    }

    fun getAllQuestions() {
        viewModelScope.launch {
            repository.getAllQuestions().collect { response ->
                _questionState.value = response
                when (response) {
                    is Response.Success -> {
                        Log.d("QuestionViewModel", "Success: ${response.data}")
                    }

                    is Response.Error -> {
                        Log.d("QuestionViewModel", "Error: ${response.exception}")
                    }

                    is Response.Loading -> {
                        Log.d("QuestionViewModel", "Loading")
                    }
                }
            }
        }
    }
}