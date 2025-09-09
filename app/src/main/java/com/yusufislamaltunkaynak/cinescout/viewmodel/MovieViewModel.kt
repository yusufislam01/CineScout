package com.yusufislamaltunkaynak.cinescout.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.service.MovieAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel: ViewModel() {

    private val BASE_URL = "https://api.themoviedb.org/3/"
    val retrofit =  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    val movieList =mutableStateOf<List<Movies>>(listOf())

    init {
        getMovies()
    }

    fun getMovies(){
        viewModelScope.launch(Dispatchers.IO) {
        }
    }

}
