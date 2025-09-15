package com.yusufislamaltunkaynak.cinescout.network.service

import com.yusufislamaltunkaynak.cinescout.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MoviesResponse
}
