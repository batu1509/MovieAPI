package com.batueksi.movieapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batueksi.movieapi.databinding.RecyclerRowMovieBinding
import com.bumptech.glide.Glide

class TvShowAdapter(val tvList : ArrayList<ResultX>, val onItemClick : (ResultX) -> Unit): RecyclerView.Adapter<TvShowAdapter.TvShowHolder>() {

    class TvShowHolder(val binding: RecyclerRowMovieBinding, val onItemClick: (ResultX) -> Unit) : RecyclerView.ViewHolder(binding.root){

        fun bind(resultX: ResultX){
            binding.recyclerViewTextView.text = resultX.name
            binding.ratingBar2.rating = resultX.vote_average.toFloat()
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${resultX.backdrop_path}")
                .into(binding.recyclerViewImageView)

            binding.root.setOnClickListener {
                onItemClick(resultX)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowHolder {
        val binding = RecyclerRowMovieBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return TvShowHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: TvShowHolder, position: Int) {
        holder.bind(tvList.get(position))
    }

    override fun getItemCount(): Int {
        return tvList.size
    }
}