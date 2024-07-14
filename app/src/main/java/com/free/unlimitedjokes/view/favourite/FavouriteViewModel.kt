package com.free.unlimitedjokes.view.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.free.unlimitedjokes.db.DefaultJokesRepository
import com.free.unlimitedjokes.db.JokesData
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DefaultJokesRepository = DefaultJokesRepository.getInstance(application)

    var getAllJokes: LiveData<List<JokesData>> = repository.getAllJokes()

    fun deleteJokes(jokesData: JokesData){
        viewModelScope.launch{
            repository.deleteJokes(jokesData)
        }
    }
}