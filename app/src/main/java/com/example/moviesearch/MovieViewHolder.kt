package com.example.moviesearch

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.data.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.movie_title)
    private val overview: TextView = itemView.findViewById(R.id.movie_overview)
    private val image: ImageView = itemView.findViewById(R.id.movie_image)

    private val IMAGE_RETRIEVAL_BASE_URL = "https://image.tmdb.org/t/p/w500/"

    fun bind(movie: Movie) {
        title.text = movie.title
        overview.text = movie.overview

        // TODO finalize how to load image, use Glide or Picasso
//        image.setImageResource()
        val imageUrlExtension = movie.backdrop_path

        Picasso.get()
            .load(IMAGE_RETRIEVAL_BASE_URL + imageUrlExtension)
            .placeholder(R.drawable.ic_baseline_local_movies_24)
            .resize(200, 200)
            .centerCrop()
            .into(image)
    }

}
