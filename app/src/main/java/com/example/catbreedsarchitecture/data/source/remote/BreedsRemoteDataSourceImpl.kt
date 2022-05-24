package com.example.catbreedsarchitecture.data.source.remote

import com.example.catbreedsarchitecture.api.ApiService
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepository
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class BreedsRemoteDataSourceImpl @Inject constructor(private val apiService : ApiService,private val localRepository: BreedsLocalRepository) : BreedsRemoteDataSource {
    override suspend fun getDefaultBreeds(): List<Breed> {
        val localResponse : List<Breed> = localRepository.readAllData()
        val remoteResponse : List<Breed> = apiService.getDefaultBreeds()

        remoteResponse.forEachIndexed { index, element ->
            localResponse.forEachIndexed { index2, element2 ->
                if (localResponse[index2].name.equals(remoteResponse[index].name)) {
                    remoteResponse[index].IsCatliked = true
                }
            }
        }
        return remoteResponse
    }
}