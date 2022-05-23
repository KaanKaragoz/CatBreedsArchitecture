package com.example.catbreedsarchitecture.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.source.remote.BreedsRemoteDataSource
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BreedsRepository) : ViewModel() {

    private val _breeds = MutableStateFlow(HomeUiState())
    val breeds : StateFlow<HomeUiState> = _breeds.asStateFlow()

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
}



data class HomeUiState(
    val breedsItems : List<Breed> = listOf()
    ) {

}




