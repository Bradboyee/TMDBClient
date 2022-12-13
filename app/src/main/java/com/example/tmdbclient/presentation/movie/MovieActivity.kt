package com.example.tmdbclient.presentation.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        (application as Injector).createMovieSubComponent().inject(this)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMovies() {
        binding.movieProgressbar.visibility = View.VISIBLE
        val response = movieViewModel.updateMovies()
        response.observe(this) {
            if (it != null) {
                adapter.setMovieList(it)
                adapter.notifyDataSetChanged()
                binding.movieProgressbar.visibility = View.GONE
            } else {
                binding.movieProgressbar.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerView() {
        adapter = MovieAdapter()
        binding.movieRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.movieRecyclerview.adapter = adapter
        displayPopularMovie()
    }

    private fun displayPopularMovie() {
        binding.movieProgressbar.visibility = View.VISIBLE
        val response = movieViewModel.getMovies()
        response.observe(this) {
            if (it != null) {
                adapter.setMovieList(it)
                adapter.notifyDataSetChanged()
                binding.movieProgressbar.visibility = View.GONE
            } else {
                binding.movieProgressbar.visibility = View.GONE
                Toast.makeText(this, "No data available", Toast.LENGTH_LONG).show()
            }
        }
    }
}