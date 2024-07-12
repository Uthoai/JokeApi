package com.free.unlimitedjokes.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.unlimitedjokes.service.JokesClient
import com.free.unlimitedjokes.service.ResponseJokes
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val jokesData = MutableLiveData<ResponseJokes>()
    val _jokesData get() = jokesData

    fun getRandomJokes() {
        viewModelScope.launch {
            val response = JokesClient.jokeApiService.getRandomJoke()
            if (response.isSuccessful) {
                jokesData.postValue(response.body())
            }
        }
    }
}


