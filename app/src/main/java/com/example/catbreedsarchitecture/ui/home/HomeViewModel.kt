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
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BreedsRepository) : ViewModel() {

    private val _breeds = MutableLiveData<List<Breed>>()
    val breeds: LiveData<List<Breed>> get() = _breeds
    val errorMessage = MutableLiveData<String>()




    fun handleBreeds() {

        viewModelScope.launch {
            Log.d("response", "coroutine")
            var response = repository.getDefaultBreeds()
            Log.d("response", "--")
            Log.d("response", response.toString())


        }

    }
}



private data class breedsUiState(
        val name: String,
        val imgLink: String,
        val description: String
    )




