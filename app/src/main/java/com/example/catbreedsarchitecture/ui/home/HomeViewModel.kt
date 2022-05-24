package com.example.catbreedsarchitecture.ui.home

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.Items
import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepository
import com.example.catbreedsarchitecture.util.downloadFromUrl
import com.example.catbreedsarchitecture.util.placeholderProgressBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BreedsRepository, private val localRepository: BreedsLocalRepository) : ViewModel() {

    private val _breeds = MutableStateFlow(HomeUiState(onFavouriteChanged = {id,isFavourited ->
        Log.d("home",id+" - "+isFavourited.toString())
        val j : Items = Items("url")
        val k : Breed = Breed(id,j,"asdasd","asd","asdas","asdas",4,true)

        viewModelScope.launch {
            localRepository.addCat(k)
            Log.d("room",localRepository.readAllData().toString())
            handleBreeds()

        }
    }))
    val breeds : StateFlow<HomeUiState> = _breeds.asStateFlow()

    fun handleBreeds() {
        viewModelScope.launch {
            Log.d("retrofit", "coroutine")
            val breedsItems = repository.getDefaultBreeds()
            Log.d("retrofit", "--")
            Log.d("retrofit", breedsItems.toString())

            _breeds.update {
                it.copy(breedsItems = breedsItems)


            }
        }
    }

    fun tryRoom() {
        viewModelScope.launch {
            //localRepository.addCat(k)
            Log.d("room",localRepository.readAllData().toString())
        }
    }

}


data class HomeUiState(
    val breedsItems : List<Breed> = listOf(),
    val onFavouriteChanged : (String?, Boolean?) -> Unit

    ) {

}




