package com.example.catbreedsarchitecture.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catbreedsarchitecture.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
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
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        initializeAdapter()
        viewModel.handleBreeds()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.breeds.collect {
                    breedsAdapter.submitList(it.breedsItems)}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeAdapter() {
        breedsAdapter = BreedsFeedAdapter(viewModel.breeds.value.onFavouriteChanged)
        binding.breedsFeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = breedsAdapter
        }

    }

}