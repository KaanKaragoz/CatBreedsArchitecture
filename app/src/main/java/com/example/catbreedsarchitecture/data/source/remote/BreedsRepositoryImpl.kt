package com.example.catbreedsarchitecture.data.source.remote

import com.example.catbreedsarchitecture.api.ApiService
import javax.inject.Inject

    class BreedsRepositoryImpl @Inject constructor (private val breedsRemoteDataSource: BreedsRemoteDataSource) : BreedsRepository {

    override suspend fun getBreeds(searchText : String,apiKey : String) = breedsRemoteDataSource.getBreeds(searchText,apiKey)
    override suspend fun getDefaultBreeds() = breedsRemoteDataSource.getDefaultBreeds()


}