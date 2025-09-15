package com.yusufislamaltunkaynak.cinescout.network.repository

import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.network.service.MovieAPI
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieAPI,
) : MovieRepository {

    companion object {
        private const val API_KEY = "8d076f84526c06844eef00e4b673b74e"
    }

    override suspend fun getPopularMovies(page: Int): List<Movies> {
        val response = api.getPopularMovies(API_KEY, page)
        return response.results
    }
}
