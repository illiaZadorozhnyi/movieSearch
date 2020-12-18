package com.example.moviesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ViewFlipper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.data.Movie
import com.example.moviesearch.data.MoviePageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieListActivity : AppCompatActivity() {

    companion object {
        private const val DISPLAY_PROGRESS = 0
        private const val DISPLAY_LIST_MOVIES = 1
        private const val DISPLAY_EMPTY_MOVIES = 2
    }

    private lateinit var movies: List<Movie>
    private lateinit var adapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var viewFlipper: ViewFlipper? = null

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        display(DISPLAY_PROGRESS)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val message: String = intent.getStringExtra(MESSAGE) ?: ""
        viewFlipper = findViewById(R.id.movies_view_flipper)

        initViews()

        retrieveMovies(message)

    }



    private fun initViews() {
        recyclerView = findViewById(R.id.movie_list_recycler)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun retrieveMovies(searchedMovie: String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieRetrievalApi = retrofit.create(MovieRetrievalApi::class.java)

        val request: Call<MoviePageResponse> =
            movieRetrievalApi.getMovies("d6113656d15b4a7ecface03b44f31731", searchedMovie)

        Log.d(TAG, "full Url: " + request.request().url())

        request.enqueue(object : Callback<MoviePageResponse> {
            override fun onFailure(call: Call<MoviePageResponse>?, t: Throwable?) {
                Log.d(TAG, "onFailure: $t.message")
            }

            override fun onResponse(call: Call<MoviePageResponse>?, response: Response<MoviePageResponse>?) {
                Log.d(TAG, "onSuccess: $response")
                if (response?.isSuccessful != true) {
                    return
                }

                val successfulResponse = response.body()
                val listOfMovies: List<Movie>? = successfulResponse?.results

                if(listOfMovies!!.isNotEmpty()) {
                    movies = listOfMovies ?: listOf()
                    adapter = MovieAdapter()
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
}