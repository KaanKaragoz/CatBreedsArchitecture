package com.example.catbreedsarchitecture.data.source.remote

import com.example.catbreedsarchitecture.api.ApiService
import com.example.catbreedsarchitecture.data.Breed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


class BreedsRemoteDataSource @Inject constructor(
    private val breedsApi: ApiService
        ) {
    suspend fun getDefaultBreeds(): List<Breed> = breedsApi.getDefaultBreeds()
    suspend fun getBreeds(searchText : String, apiKey : String): List<Breed> = breedsApi.getBreeds(searchText,apiKey)
    }



