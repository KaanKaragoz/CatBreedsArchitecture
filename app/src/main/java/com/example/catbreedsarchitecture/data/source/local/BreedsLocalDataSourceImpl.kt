package com.example.catbreedsarchitecture.data.source.local

import com.example.catbreeds.room.BreedsDao
import com.example.catbreedsarchitecture.api.ApiService
import com.example.catbreedsarchitecture.data.Breed
import javax.inject.Inject

class BreedsLocalDataSourceImpl @Inject constructor(private val breedsDao : BreedsDao) :BreedsLocalDataSource {
    override suspend fun readAllData(): List<Breed> {
        return breedsDao.readAllData()
    }

    override suspend fun addCat(cat: Breed) {
        return breedsDao.addCat(cat)
    }

}