package com.example.catbreedsarchitecture.data.source.local

import com.example.catbreedsarchitecture.data.Breed

interface BreedsLocalRepository {
    suspend fun readAllData()  : List<Breed>
    suspend fun addCat(breed : Breed)
}