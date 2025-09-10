package com.yusufislamaltunkaynak.cinescout.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.network.service.MovieAPI
import com.yusufislamaltunkaynak.cinescout.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {
    private val _movies = MutableStateFlow<List<Movies>>(emptyList())
    val movies: StateFlow<List<Movies>> = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val result = getPopularMoviesUseCase()
                _movies.value = result
            } catch (e: Exception) {
                // Hata yönetimi
                Log.e("MovieViewModel", "API Error: ${e.message}")
            }
        }
    }

}
