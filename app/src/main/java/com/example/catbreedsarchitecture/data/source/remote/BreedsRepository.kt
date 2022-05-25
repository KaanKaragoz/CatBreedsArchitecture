package com.example.catbreedsarchitecture.data.source.remote

import com.example.catbreedsarchitecture.data.Breed


interface BreedsRepository {

    suspend fun getDefaultBreeds() : List<Breed>

}