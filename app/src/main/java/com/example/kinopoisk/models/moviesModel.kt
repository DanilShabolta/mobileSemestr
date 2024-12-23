package com.example.kinopoisk.models

data class moviesModel(
    val total: Int,
    val totalPages: Int,
    val items: List<movieItemModel>
)
