package com.yusufislamaltunkaynak.cinescout.network.repository

import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.network.service.MovieAPI
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieAPI,
) : MovieRepository {
    override suspend fun getPopularMovies(): List<Movies> {
        return api.getData()
    }
}