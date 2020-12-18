package com.example.moviesearch.views

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R
import com.example.moviesearch.models.Movie
import com.squareup.picasso.Picasso


/**
 * ViewHolder class for [MovieAdapter] that handles binding of text and image for each item.
 */
class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val IMAGE_RETRIEVAL_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    private val title: TextView = itemView.findViewById(R.id.movie_title)
    private val overview: TextView = itemView.findViewById(R.id.movie_overview)
    private val imageView: ImageView = itemView.findViewById(R.id.movie_image)

    fun bind(movie: Movie) {
        title.text = movie.title
        overview.text = movie.overview
        bindImage(movie, imageView)
    }

    private fun bindImage(movie: Movie, image: ImageView) {
        val imageUrlSuffix = movie.backdrop_path

        Picasso.get()
            .load(IMAGE_RETRIEVAL_BASE_URL + imageUrlSuffix)
            .placeholder(R.drawable.ic_movies_placeholder)
            .resize(200, 200)
            .centerCrop()
            .into(image)
    }
}
