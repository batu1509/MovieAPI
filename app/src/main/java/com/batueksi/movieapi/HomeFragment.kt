package com.batueksi.movieapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.batueksi.movieapi.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.filmbutton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_movieListFragment)
        }
        binding.tvbutton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tvShowListFragment)
        }

        return binding.root
    }



}