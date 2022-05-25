package com.example.catbreedsarchitecture.data.source.local

import com.example.catbreedsarchitecture.data.Breed
import javax.inject.Inject

class FavoriteBreedsRepositoryImpl @Inject constructor(
    private val favoriteBreedsLocalDataSource: FavoriteBreedsLocalDataSource) :FavoriteBreedsRepository {

    override suspend fun readAllData(): List<Breed> = favoriteBreedsLocalDataSource.readAllData()

    override suspend fun addCat(breed :Breed) = favoriteBreedsLocalDataSource.addCat(breed)

    override suspend fun deleteCat(cat: String) = favoriteBreedsLocalDataSource.deleteCat(cat)

}