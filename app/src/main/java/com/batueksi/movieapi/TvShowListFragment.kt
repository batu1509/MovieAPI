package com.batueksi.movieapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batueksi.movieapi.databinding.FragmentTvShowListBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TvShowListFragment : Fragment() {

    private var job : Job? = null
    private var tvShowAdapter: TvShowAdapter? = null
    private var tvShowResult: ArrayList<ResultX>? = null

    private lateinit var binding : FragmentTvShowListBinding

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowListBinding.inflate(inflater, container, false)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.recyclerviewTvshow.layoutManager = layoutManager

        loadData()
        return binding.root
    }

        private fun loadData(){

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(interfaceAPI2::class.java)

            job = CoroutineScope(Dispatchers.IO).launch {
                val response = retrofit.getData(Constants.apikey)

                withContext(Dispatchers.Main + exceptionHandler){
                    if (response.isSuccessful){
                        response.body()?.let{
                            tvShowResult = ArrayList(it.results)
                            tvShowResult?.let{
                                tvShowAdapter = TvShowAdapter(it) {resultX ->
                                    findNavController().navigate(R.id.action_tvShowListFragment_to_tvShowDetailsFragment, bundleOf("result_arg" to resultX))
                                }
                                binding.recyclerviewTvshow.adapter = tvShowAdapter
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