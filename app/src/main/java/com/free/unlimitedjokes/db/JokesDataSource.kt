package com.free.unlimitedjokes.db

import androidx.lifecycle.LiveData

interface JokesDataSource {

    suspend fun saveJokes(jokesData: JokesData)

    fun getAllJokes(): LiveData<List<JokesData>>

    suspend fun deleteJokes(jokesData: JokesData)
}