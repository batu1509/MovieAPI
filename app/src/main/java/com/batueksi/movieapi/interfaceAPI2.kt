package com.batueksi.movieapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface interfaceAPI2 {

    @GET("tv/popular")

    suspend fun getData(@Query("api_key") apiKey: String): Response<PopularTvShow>
}