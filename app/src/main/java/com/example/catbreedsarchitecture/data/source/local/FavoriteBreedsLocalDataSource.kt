package com.example.catbreedsarchitecture.data.source.local


import com.example.catbreedsarchitecture.data.Breed

interface FavoriteBreedsLocalDataSource {

    suspend fun readAllData(): List<Breed>

    suspend fun addCat(cat: Breed)

    suspend fun deleteCat(catName: String)
}