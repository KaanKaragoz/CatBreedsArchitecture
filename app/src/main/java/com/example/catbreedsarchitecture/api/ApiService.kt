package com.example.catbreedsarchitecture.api

import com.example.catbreedsarchitecture.data.Breed
import retrofit2.http.GET


interface ApiService {
    @GET("breeds/")
   suspend fun getDefaultBreeds(
    ): List<Breed>
}