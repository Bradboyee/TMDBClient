package com.example.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.tvshow.TVShow
import com.example.tmdbclient.databinding.ListItemBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {
    private val tvShowList = ArrayList<TVShow>()

    fun setTvShowList(tvShowList: List<TVShow>) {
        this.tvShowList.clear()
        this.tvShowList.addAll(tvShowList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(tvShowList[position])
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TVShow) {
            val posterURL = "https://image.tmdb.org/t/p/w500" + tvShow.posterPath
            with(binding) {
                titleTextView.text = tvShow.name
                descriptionTextView.text = tvShow.overview
                Glide.with(imageView.context).load(posterURL).into(imageView)
            }
        }
    }
}