package com.example.moviesearch

import com.example.moviesearch.data.Movie
import com.example.moviesearch.data.MoviePagesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRetrievalApi {

    @GET("search/movie")
    fun getMovies(@Query("api_key") api_key: String,
                  @Query("query") query: String) : Call<MoviePagesResponse>

}