package com.example.catbreedsarchitecture.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.Items
import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BreedsRepository, private val localRepository: BreedsLocalRepository) : ViewModel() {

    private val _breeds = MutableStateFlow(HomeUiState())
    val breeds : StateFlow<HomeUiState> = _breeds.asStateFlow()
    val j : Items = Items("url")
    val k : Breed = Breed("kedi2",j,"asdasd","asd","asdas","asdas",4,true)

    fun handleBreeds() {
        viewModelScope.launch {
            Log.d("response", "coroutine")
            val breedsItems = repository.getDefaultBreeds()
            Log.d("response", "--")
            Log.d("response", breedsItems.toString())

            _breeds.update {
                it.copy(breedsItems = breedsItems)

            }
        }
    }

    fun tryRoom() {
        viewModelScope.launch {
            localRepository.addCat(k)
            Log.d("room",localRepository.readAllData().toString())
        }
    }
}


data class HomeUiState(
    val breedsItems : List<Breed> = listOf()
    ) {

}




