package com.example.catbreedsarchitecture.data.source.remote


import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor (private val breedsRemoteDataSource: BreedsRemoteDataSource) : BreedsRepository {

    override suspend fun getDefaultBreeds() = breedsRemoteDataSource.getDefaultBreeds()

}