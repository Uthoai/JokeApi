package com.free.unlimitedjokes.db.local

import androidx.lifecycle.LiveData
import com.free.unlimitedjokes.db.JokesData
import com.free.unlimitedjokes.db.JokesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(
    private val dao: JokesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): JokesDataSource {
    override suspend fun saveJokes(jokesData: JokesData) {
        withContext(ioDispatcher){
            dao.insert(jokesData)
        }
    }

    override fun getAllJokes(): LiveData<List<JokesData>> {
        return dao.getAllJokes()
    }

    override suspend fun deleteJokes(jokesData: JokesData) {
        withContext(ioDispatcher){
            dao.deleteJokes(jokesData)
        }
    }
}