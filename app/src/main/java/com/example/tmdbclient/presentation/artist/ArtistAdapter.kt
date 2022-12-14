package com.example.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.databinding.ListItemBinding

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.MyViewHolder>() {
    private val artistList = ArrayList<Artist>()

    fun setArtistList(artistList: List<Artist>) {
        this.artistList.clear()
        this.artistList.addAll(artistList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(artistList[position])
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: Artist) {
            val posterURL = "https://image.tmdb.org/t/p/w500" + artist.profilePath
            with(binding) {
                titleTextView.text = artist.name
                descriptionTextView.text = artist.popularity.toString()
                Glide.with(imageView.context).load(posterURL).into(imageView)
            }
        }
    }
}