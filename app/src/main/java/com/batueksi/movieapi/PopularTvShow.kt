package com.batueksi.movieapi

data class PopularTvShow(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)