package com.example.moviesearch.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesearch.models.FavoriteMovie
import com.example.moviesearch.models.Movie

@Dao
interface FavoriteMovieDao {

    @Insert
    fun insert(favMovie: FavoriteMovie)

    @Delete
    fun delete(favMovie: FavoriteMovie)

    @Query("DELETE FROM fav_movie_table")
    fun deleteAllFavoriteMovies()

    @Query("SELECT * FROM fav_movie_table ORDER BY popularity")
    fun getAllFavoriteMovies(): List<FavoriteMovie>

}