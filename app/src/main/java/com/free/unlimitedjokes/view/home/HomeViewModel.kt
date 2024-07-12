package com.free.unlimitedjokes.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.unlimitedjokes.service.JokesClient
import com.free.unlimitedjokes.service.ResponseJokes
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val jokesData = MutableLiveData<ResponseJokes>()
    val _jokesData get() = jokesData

    fun getRandomJokes(category: String) {
        viewModelScope.launch {
            val response = when (category) {
                "Any" -> JokesClient.jokeApiService.getRandomJoke()
                "Programming" -> JokesClient.jokeApiService.getProgrammingJoke()
                "Miscellaneous" -> JokesClient.jokeApiService.getMiscellaneousJoke()
                "Dark" -> JokesClient.jokeApiService.getDarkJoke()
                "Pun" -> JokesClient.jokeApiService.getPunJoke()
                "Spooky" -> JokesClient.jokeApiService.getSpookyJoke()
                "Christmas" -> JokesClient.jokeApiService.getChristmasJoke()
                else -> JokesClient.jokeApiService.getRandomJoke()
            }
            if (response.isSuccessful) {
                jokesData.postValue(response.body())
            }
        }
    }

    /*fun saveTask() {
        val currentTitle = title.value
        val currentDescription = description.value
        val task = Task(
            id = currentTaskID,
            title = currentTitle.toTrimString(),
            description = currentDescription.toTrimString()
        )

        if (!isValidTask(currentTitle, currentDescription)) {
            return
        }

        if (currentTaskID == noTaskID) {
            createTask(task)
        } else {
            updateTask(task)
        }

    }*/
}


