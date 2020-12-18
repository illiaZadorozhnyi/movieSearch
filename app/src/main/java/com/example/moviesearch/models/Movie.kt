package com.example.moviesearch.models

data class Movie(
    val adult: Boolean = false,
    val backdrop_path: String? = null,
    val genre_ids: List<Int> = listOf(),
    val id: Long? = null,
    val original_language: String = "en",
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Float = 0.0f,
    val posted_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean = false,
    val vote_average: Float = 0.0f,
    val vote_count: Int = 0
)