package com.batueksi.movieapi


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface interfaceAPI {

    @GET("movie/popular")


    suspend fun getData(@Query("api_key") apiKey: String): Response<PopularMovieResponse>


}