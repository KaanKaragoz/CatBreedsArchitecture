package com.example.catbreedsarchitecture.data.source.remote

import com.example.catbreedsarchitecture.data.Breed
import dagger.Provides
import retrofit2.Call
import javax.inject.Singleton


interface BreedsRepository {
    suspend fun getBreeds(searchText : String,id : String)  : List<Breed>
    suspend fun getDefaultBreeds() : List<Breed>
}