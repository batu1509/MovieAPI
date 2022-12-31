package com.batueksi.movieapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.batueksi.movieapi.databinding.FragmentTvShowDetailsBinding
import com.bumptech.glide.Glide


class TvShowDetailsFragment : Fragment() {

    private lateinit var binding : FragmentTvShowDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val resultX = arguments?.getParcelable<ResultX>("result_arg")
        binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
        binding.Tvbutton1.setOnClickListener {
            activity?.onBackPressed()
        }

        if (resultX != null) {
            binding.TvDetailsViewTitle.text = resultX.name
            binding.TvDetailsViewDate.text = resultX.first_air_date

            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${resultX.backdrop_path}")
                .into(binding.TvrecyclerViewImageView)

            binding.TvDetailsViewDescription.text = resultX.overview
        }


        return binding.root
    }


}