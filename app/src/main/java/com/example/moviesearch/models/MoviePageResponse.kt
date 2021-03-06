package com.example.moviesearch.models

data class MoviePageResponse(
    val page: Int,
    val results: List<Movie> = listOf(),
    val total_pages: Int,
    val total_results: Int
)
