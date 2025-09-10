package com.yusufislamaltunkaynak.cinescout.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movies>>(emptyList())
    val movies: StateFlow<List<Movies>> = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var currentPage = 1
    private var isLastPage = false

    init {
        loadNextPage() // İlk sayfayı yükle
    }

    fun loadNextPage() {
        // Eğer zaten yükleniyorsa veya son sayfa geldiyse yeni veri çekme
        if (_isLoading.value || isLastPage) return

        viewModelScope.launch {
            try {
                _isLoading.value = true
                val newMovies = getPopularMoviesUseCase(currentPage)

                if (newMovies.isEmpty()) {
                    isLastPage = true
                } else {
                    // Mevcut listeye yeni sayfayı ekle
                    _movies.value = _movies.value + newMovies
                    currentPage++
                }

            } catch (e: Exception) {
                _error.value = e.message ?: "Bilinmeyen hata"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
