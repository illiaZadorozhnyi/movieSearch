package com.example.moviesearch.data

data class MoviePagesResponse(
    val page: Int,
    val results: List<Movie> = listOf(),
    val total_pages: Int,
    val total_results: Int
)
