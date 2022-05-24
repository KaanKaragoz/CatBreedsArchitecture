package com.example.catbreedsarchitecture.data.source.local

import com.example.catbreedsarchitecture.data.Breed
import javax.inject.Inject

class BreedsLocalRepositoryImpl @Inject constructor(private val breedsLocalDataSource: BreedsLocalDataSource) :BreedsLocalRepository {

    override suspend fun readAllData(): List<Breed> = breedsLocalDataSource.readAllData()

    override suspend fun addCat(breed :Breed) = breedsLocalDataSource.addCat(breed)

    override suspend fun deleteCat(cat: String) = breedsLocalDataSource.deleteCat(cat)

}