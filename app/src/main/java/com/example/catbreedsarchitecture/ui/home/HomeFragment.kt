package com.example.catbreedsarchitecture.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var viewModel : HomeViewModel
    lateinit var breedsAdapter : BreedsFeedAdapter
    private  var  _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var breedsItems : List<Breed> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        breedsAdapter = BreedsFeedAdapter()
        binding.breedsFeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = breedsAdapter
        }

        viewModel.handleBreeds()

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.breeds.collect{
                breedsItems = it.breedsItems
                Log.d("home",breedsItems.toString())

                breedsAdapter.submitList(breedsItems)

            }
        }
        setupOnClickListeners()

    }

    fun setupOnClickListeners(){
        binding.button.setOnClickListener {

        }

    }



}