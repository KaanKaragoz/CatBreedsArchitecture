package com.example.catbreedsarchitecture.data.source.local

import com.example.catbreedsarchitecture.data.Breed
import javax.inject.Inject

class FavoriteBreedsLocalDataSourceImpl @Inject constructor(private val breedsDao : FavoriteBreedsDao) :FavoriteBreedsLocalDataSource {

    override suspend fun readAllData(): List<Breed> = breedsDao.readAllData()

    override suspend fun addCat(cat: Breed) = breedsDao.addCat(cat)

    override suspend fun deleteCat(catName: String) = breedsDao.deleteCat(catName)

}