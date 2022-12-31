package com.batueksi.movieapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batueksi.movieapi.databinding.FragmentMovieListBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieListFragment : Fragment() {

    private var job : Job? = null
    private var movieResult: ArrayList<Result>? = null
    private var movieAdapter : MovieAdapter? = null

    private lateinit var binding: FragmentMovieListBinding

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        //RecyclerView
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.recyclerviewMovies.layoutManager = layoutManager


        loadData()
        return binding.root

    }


    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(interfaceAPI::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getData(Constants.apikey)

            withContext(Dispatchers.Main + exceptionHandler){
                if (response.isSuccessful){
                    response.body()?.let{
                        movieResult = ArrayList(it.results)
                        movieResult?.let {
                            movieAdapter = MovieAdapter(it) { result->
                                findNavController().navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundleOf("result_arg" to result))

                            }
                            binding.recyclerviewMovies.adapter = movieAdapter
                        }
                    }
                }
            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
    }

}