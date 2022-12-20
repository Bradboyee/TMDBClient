package com.example.tmdbclient.presentation.tvshow

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
import com.example.tmdbclient.databinding.ActivityTvShowBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvShowActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter

    private lateinit var binding: ActivityTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)

        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]



        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = TvShowAdapter()
        binding.tvShowRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.tvShowRecyclerview.adapter = adapter
        displayPopularTvShow()
    }

    private fun displayPopularTvShow() {
        binding.tvShowProgressbar.visibility = View.VISIBLE
        val response = tvShowViewModel.getTvShow()
        response.observe(this) {
            if (it != null) {
                adapter.setTvShowList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowProgressbar.visibility = View.GONE
            } else {
                binding.tvShowProgressbar.visibility = View.GONE
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
                updateTvShow()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateTvShow() {
        binding.tvShowProgressbar.visibility = View.VISIBLE
        val response = tvShowViewModel.updateTvShow()
        response.observe(this) {
            if (it != null) {
                adapter.setTvShowList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowProgressbar.visibility = View.GONE
            } else {
                binding.tvShowProgressbar.visibility = View.GONE
            }
        }
    }
}