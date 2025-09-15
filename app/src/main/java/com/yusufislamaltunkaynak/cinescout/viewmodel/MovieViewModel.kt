package com.yusufislamaltunkaynak.cinescout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MoviesUiState {
    data object Loading : MoviesUiState
    data class Success(val movies: List<Movies>) : MoviesUiState
    data class Error(val message: String) : MoviesUiState
}

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    private var currentPage = 1
    private var isLastPage = false

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLastPage) return

        viewModelScope.launch {
            try {
                val newMovies = getPopularMoviesUseCase(currentPage)

                if (newMovies.isEmpty()) {
                    isLastPage = true
                } else {
                    val updatedMovies = when (val state = _uiState.value) {
                        is MoviesUiState.Success -> state.movies + newMovies
                        else -> newMovies
                    }
                    _uiState.value = MoviesUiState.Success(updatedMovies)
                    currentPage++
                }
            } catch (e: Exception) {
                _uiState.value = MoviesUiState.Error(e.message ?: "Bilinmeyen hata")
            }
        }
    }
}
