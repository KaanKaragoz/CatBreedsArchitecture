package com.example.catbreedsarchitecture.domain

import com.example.catbreedsarchitecture.api.ApiService
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRemoteDataSource
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepository
import javax.inject.Inject

class FavCatComparisonUseCase @Inject constructor(
    private val breedsRepository: BreedsRepository,
    private val localRepository: BreedsLocalRepository) {

       private suspend fun combineData(): List<Breed> {
        val localResponse : List<Breed> = localRepository.readAllData()
        val remoteResponse : List<Breed> = breedsRepository.getDefaultBreeds()

        remoteResponse.forEachIndexed { index, element ->
            localResponse.forEachIndexed { index2, element2 ->
                if (localResponse[index2].name.equals(remoteResponse[index].name)) {
                    remoteResponse[index].IsCatliked = true
                }
            }
        }
        return remoteResponse
    }

    suspend operator fun invoke(): List<Breed> {
        return combineData()
    }
}