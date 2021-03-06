package com.example.catbreedsarchitecture.data.source.remote

import com.example.catbreedsarchitecture.api.ApiService
import com.example.catbreedsarchitecture.data.Breed
import javax.inject.Inject

class BreedsRemoteDataSourceImpl @Inject constructor(private val apiService : ApiService) : BreedsRemoteDataSource {

    override suspend fun getDefaultBreeds(): List<Breed> = apiService.getDefaultBreeds()

}