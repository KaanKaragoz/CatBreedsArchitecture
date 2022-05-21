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
   // private val breedsRemoteDataSource: BreedsRemoteDataSource = BreedsRemoteDataSource.create()
   // private var repository: BreedsRepositoryImpl = BreedsRepositoryImpl(breedsRemoteDataSource)




    fun handleBreeds() {

        viewModelScope.launch {
            Log.d("response", "coroutine")
            var response = repository.getDefaultBreeds()
            Log.d("response", "--")
            Log.d("response", response.toString())

            /*
            response.enqueue(object : Callback<List<Breed>> {
                override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                    Log.d("response", response.body().toString())
                }

                override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
*/
        }

    }
}



private data class breedsUiState(
        val name: String,
        val imgLink: String,
        val description: String
    )




