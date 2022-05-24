package com.example.catbreedsarchitecture.data.source.remote

import com.example.catbreedsarchitecture.data.Breed

interface BreedsRemoteDataSource {
    suspend fun getDefaultBreeds(): List<Breed>
}