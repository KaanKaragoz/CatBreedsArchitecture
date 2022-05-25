package com.example.catbreedsarchitecture.data.source.local

import com.example.catbreedsarchitecture.data.Breed

interface FavoriteBreedsRepository {

    suspend fun readAllData()  : List<Breed>

    suspend fun addCat(breed : Breed)

    suspend fun deleteCat(cat : String)

}