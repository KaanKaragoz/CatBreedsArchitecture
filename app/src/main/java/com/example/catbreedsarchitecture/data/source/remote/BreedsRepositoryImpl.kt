package com.example.catbreedsarchitecture.data.source.remote

import com.example.catbreedsarchitecture.api.ApiService
import com.example.catbreedsarchitecture.data.Breed
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor (private val breedsRemoteDataSource: BreedsRemoteDataSourcee) : BreedsRepository {
    override suspend fun getBreeds(searchText: String, id: String): List<Breed> {
        TODO("Not yet implemented")
    }

    // override suspend fun getBreeds(searchText : String,apiKey : String) = breedsRemoteDataSource.getBreeds(searchText,apiKey)
    override suspend fun getDefaultBreeds() = breedsRemoteDataSource.getDefaultBreeds()

}