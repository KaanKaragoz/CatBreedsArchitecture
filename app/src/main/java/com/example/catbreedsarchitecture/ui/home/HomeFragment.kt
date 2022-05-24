package com.example.catbreedsarchitecture.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var viewModel : HomeViewModel
    lateinit var breedsAdapter : BreedsFeedAdapter
    private  var  _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this.viewLifecycleOwner // viewLifecycleOwner =? getViewLifecycleOwner
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        initializeAdapter()
        setupOnClickListeners()
        viewModel.handleBreeds()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Bind the visibility of the progressBar to the state
                // of isFetchingArticles.
                viewModel.breeds.collect {
                    breedsAdapter.submitList(it.breedsItems) }
            }
        }


    /*
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.breeds.collect{
                Log.d("home",it.breedsItems.toString())
                breedsAdapter.submitList(it.breedsItems)
            }
        } */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupOnClickListeners(){
        binding.button.setOnClickListener {
            viewModel.tryRoom()
        }
    }

    private fun initializeAdapter() {
        breedsAdapter = BreedsFeedAdapter(viewModel.breeds.value.onFavouriteChanged)
        binding.breedsFeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = breedsAdapter
        }

    }

}