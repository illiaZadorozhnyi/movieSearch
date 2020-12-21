package com.example.moviesearch.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.repository.MovieOperationsApi
import com.example.moviesearch.R
import com.example.moviesearch.models.Movie
import com.example.moviesearch.models.MoviePageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Activity that shows list of items on successful retrieval via api,
 * progress bar while items are being retrieved and empty screen in case if search term is invalid
 */
class MovieListActivity : AppCompatActivity() {

    companion object {
        private const val DISPLAY_EMPTY_MOVIES = 0
        private const val DISPLAY_PROGRESS = 1
        private const val DISPLAY_LIST_MOVIES = 2
        private const val TAG = "MovieListActivity"

    }

    private lateinit var inputField: EditText
    private lateinit var searchButton: Button
    private lateinit var movies: List<Movie>
    private lateinit var adapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var viewFlipper: ViewFlipper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        display(DISPLAY_EMPTY_MOVIES)

        viewFlipper = findViewById(R.id.movies_view_flipper)
        adapter = MovieAdapter()

        initViews()
        setOnClickListener()
    }


    private fun initViews() {
        recyclerView = findViewById(R.id.movie_list_recycler)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        inputField = findViewById(R.id.input_field)
        searchButton = findViewById(R.id.button_search)
        searchButton.text = "SEARCH"
    }


    private fun retrieveMovies(searchTerm: String) {
        val apiKey = resources.getString(R.string.apiKey)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val movieRetrievalApi = retrofit.create(MovieOperationsApi::class.java)
        val request: Call<MoviePageResponse> = movieRetrievalApi.getMovies(apiKey, searchTerm)

        request.enqueue(object : Callback<MoviePageResponse> {
            override fun onFailure(call: Call<MoviePageResponse>?, t: Throwable?) {
                Log.d(TAG, "onFailure: $t.message")
            }

            override fun onResponse(
                call: Call<MoviePageResponse>?,
                response: Response<MoviePageResponse>?
            ) {
                Log.d(TAG, "onSuccess: $response")
                if (response?.isSuccessful != true) {
                    return
                }

                val successfulResponse = response.body()
                val listOfMovies = successfulResponse?.results

                if (listOfMovies!!.isNotEmpty()) {
                    movies = listOfMovies
                    adapter.setData(movies)
                    recyclerView.adapter = adapter
                    display(DISPLAY_LIST_MOVIES)
                } else {
                    display(DISPLAY_EMPTY_MOVIES)
                }
            }
        })
    }

    private fun display(which: Int) {
        if (viewFlipper?.displayedChild != which) {
            viewFlipper?.displayedChild = which
        }
    }

    private fun setOnClickListener() {
        searchButton.setOnClickListener {
            val text = inputField.text.toString()

            if(text.isNotEmpty()){
                retrieveMovies(text)
            } else {
                Toast.makeText(this, "Movie name missing...", Toast.LENGTH_LONG).show()
            }
        }
    }
}