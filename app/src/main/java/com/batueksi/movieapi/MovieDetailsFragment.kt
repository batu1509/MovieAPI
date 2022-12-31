package com.batueksi.movieapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.batueksi.movieapi.databinding.FragmentMovieDetailsBinding
import com.batueksi.movieapi.databinding.FragmentMovieListBinding
import com.bumptech.glide.Glide


class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val result = arguments?.getParcelable<Result>("result_arg")
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.button1.setOnClickListener {
            activity?.onBackPressed()

        }
        if (result != null) {
            binding.detailsViewTitle.text = result.title
            binding.detailsViewDate.text = result.release_date

            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${result.backdrop_path}")
                .into(binding.recyclerViewImageView)

            binding.detailsViewDescription.text = result.overview
        }

        return binding.root

    }





}