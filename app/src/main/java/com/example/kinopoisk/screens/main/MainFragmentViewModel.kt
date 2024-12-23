package com.example.kinopoisk.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.data.retrofit.RetrofitRepozitory
import com.example.kinopoisk.models.moviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel: ViewModel() {
    private val repository = RetrofitRepozitory()
    val myMovies: MutableLiveData<Response<moviesModel>> = MutableLiveData()

    fun getMovies(){
        viewModelScope.launch {
            try {
                myMovies.value = repository.getMovies()
            } catch (e: Exception) {
                //Toast.makeText(MAIN, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}