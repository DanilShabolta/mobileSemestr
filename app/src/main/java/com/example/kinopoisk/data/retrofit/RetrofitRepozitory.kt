package com.example.kinopoisk.data.retrofit

import com.example.kinopoisk.data.retrofit.api.RetrofitInstance
import com.example.kinopoisk.models.moviesModel
import retrofit2.Response

class RetrofitRepozitory {
    suspend fun getMovies(): Response<moviesModel> {
        return RetrofitInstance.api.getPopularMovie()
    }
}