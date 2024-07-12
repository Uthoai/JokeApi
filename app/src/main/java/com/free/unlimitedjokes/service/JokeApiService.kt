package com.free.unlimitedjokes.service

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JokeApiService {

    @GET("joke/Any")
    suspend fun getRandomJoke(): Response<ResponseJokes>

}

object JokesClient{
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://v2.jokeapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val jokeApiService = retrofit.create(JokeApiService::class.java)

}

