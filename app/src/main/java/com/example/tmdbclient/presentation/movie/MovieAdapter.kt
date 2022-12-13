package com.example.tmdbclient.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.databinding.ListItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    private val movieList = ArrayList<Movie>()

    fun setMovieList(movieList: List<Movie>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding= DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movie = movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            val posterURL = "https://image.tmdb.org/t/p/w500" + movie.posterPath
            with(binding) {
                titleTextView.text = movie.title
                descriptionTextView.text = movie.overview
                Glide.with(imageView.context).load(posterURL).into(imageView)
            }
        }
    }
}
