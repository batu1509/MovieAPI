package com.batueksi.movieapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batueksi.movieapi.databinding.RecyclerRowMovieBinding
import com.bumptech.glide.Glide

class MovieAdapter(val movieList : ArrayList<Result>, val onItemClick: (Result) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    class MovieHolder(val binding : RecyclerRowMovieBinding, val onItemClick: (Result) -> Unit) : RecyclerView.ViewHolder(binding.root){

        fun bind(result: Result) {
            binding.recyclerViewTextView.text = result.title
            binding.ratingBar2.rating = result.vote_average.toFloat()
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${result.backdrop_path}")
                .into(binding.recyclerViewImageView)

            binding.root.setOnClickListener {
                onItemClick(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = RecyclerRowMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList.get(position))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}