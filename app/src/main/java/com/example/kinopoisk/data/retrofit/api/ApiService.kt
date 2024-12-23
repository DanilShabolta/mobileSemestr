package com.example.kinopoisk.data.retrofit.api

import com.example.kinopoisk.models.moviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @GET("api/v2.2/films/collections?type=TOP_POPULAR_MOVIES&page=1")
    @Headers(
        "X-API-KEY: ebac1e80-6ea0-4a9f-a784-c5024cd35f6c",
        "Content-Type: application/json"
    )
    suspend fun getPopularMovie(): Response<moviesModel>
}