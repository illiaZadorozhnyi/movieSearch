package com.example.moviesearch.repository

import com.example.moviesearch.models.MoviePageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface that handles operations with movies via api.
 * Belongs to repository layer if caching or db retrieval is also possible.
 */
interface MovieOperationsApi {

    /**
     * Retrieves list of movies. Requires 2 params
     * @param api_key key generated under settings on base url site per user and stored in local.properties
     * @param query search parameter to use for searching
     */
    @GET("search/movie")
    fun getMovies(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Call<MoviePageResponse>

}