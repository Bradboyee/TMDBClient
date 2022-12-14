package com.example.tmdbclient.presentation.artist

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
import com.example.tmdbclient.databinding.ActivityArtistBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter

    private lateinit var binding: ActivityArtistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        artistViewModel = ViewModelProvider(this, factory)[ArtistViewModel::class.java]

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ArtistAdapter()
        binding.artistRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.artistRecyclerview.adapter = adapter
        displayPopularArtist()
    }

    private fun displayPopularArtist() {
        binding.artistProgressbar.visibility = View.VISIBLE
        val response = artistViewModel.getArtist()
        response.observe(this) {
            if (it != null) {
                adapter.setArtistList(it)
                adapter.notifyDataSetChanged()
                binding.artistProgressbar.visibility = View.GONE
            } else {
                binding.artistProgressbar.visibility = View.GONE
                Toast.makeText(this, "No data available", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateArtist()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateArtist() {
        binding.artistProgressbar.visibility = View.VISIBLE
        val response = artistViewModel.updateArtist()
        response.observe(this) {
            if (it != null) {
                adapter.setArtistList(it)
                adapter.notifyDataSetChanged()
                binding.artistProgressbar.visibility = View.GONE
            } else {
                binding.artistProgressbar.visibility = View.GONE
            }
        }
    }
}