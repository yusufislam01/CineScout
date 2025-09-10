package com.yusufislamaltunkaynak.cinescout.network.service

import com.yusufislamaltunkaynak.cinescout.model.Movies
import retrofit2.http.GET

interface MovieAPI {
    @GET("movie/popular?api_key=8d076f84526c06844eef00e4b673b74e")
    suspend fun  getData(): List<Movies>

}