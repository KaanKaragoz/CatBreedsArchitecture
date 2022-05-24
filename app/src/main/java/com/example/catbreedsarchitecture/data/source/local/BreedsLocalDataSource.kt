package com.example.catbreedsarchitecture.data.source.local

import androidx.lifecycle.LiveData
import com.example.catbreedsarchitecture.data.Breed

interface BreedsLocalDataSource {
    suspend fun readAllData(): List<Breed>
    suspend fun addCat(cat: Breed)
    suspend fun deleteCat(catName: String)
}