package com.example.catbreedsarchitecture.api

import com.example.catbreedsarchitecture.data.Breed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("breeds/search")
   suspend fun getBreeds(
        @Query("q") searchText : String,
        @Query("api_key") apiKey : String
    ): List<Breed>

    @GET("breeds/")
   suspend fun getDefaultBreeds(
    ): List<Breed>
}