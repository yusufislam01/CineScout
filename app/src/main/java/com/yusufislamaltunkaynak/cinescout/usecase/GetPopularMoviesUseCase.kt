package com.yusufislamaltunkaynak.cinescout.usecase

import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.network.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): List<Movies> {
        return repository.getPopularMovies()
    }
}
