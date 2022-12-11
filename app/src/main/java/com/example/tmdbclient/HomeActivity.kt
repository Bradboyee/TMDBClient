package com.example.tmdbclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tmdbclient.databinding.ActivityHomeBinding
import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.movie.MovieActivity
import com.example.tmdbclient.presentation.tvshow.TvShowActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        with(binding) {
            movieButton.setOnClickListener {
                val intent = Intent(this@HomeActivity, MovieActivity::class.java)
                startActivity(intent)
            }
            artistsButton.setOnClickListener {
                val intent = Intent(this@HomeActivity, ArtistActivity::class.java)
                startActivity(intent)
            }
            tvButton.setOnClickListener {
                val intent = Intent(this@HomeActivity, TvShowActivity::class.java)
                startActivity(intent)
            }
        }

    }
}