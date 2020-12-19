package com.example.moviesearch.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_movie_table")
data class FavoriteMovie(
    @PrimaryKey
    var id: Long? = null,

    var title: String? = null,
    var overview: String? = null,
    var popularity: Float = 0.0f,
    var release_date: String? = null,
    var vote_average: Float = 0.0f,
    var imageUrlSuffix: String? = null
) {
    constructor(movie: Movie) : this() {
        id = movie.id
        title = movie.original_title
        overview = movie.overview
        popularity = movie.popularity

        release_date = movie.release_date
        vote_average = movie.vote_average
        imageUrlSuffix = movie.backdrop_path
    }
}

