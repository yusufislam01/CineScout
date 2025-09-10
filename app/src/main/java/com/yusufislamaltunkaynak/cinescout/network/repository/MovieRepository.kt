package com.yusufislamaltunkaynak.cinescout.network.repository

import com.yusufislamaltunkaynak.cinescout.model.Movies

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movies>
}
