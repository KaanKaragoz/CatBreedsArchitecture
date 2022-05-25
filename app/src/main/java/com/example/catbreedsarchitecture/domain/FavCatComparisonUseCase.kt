package com.example.catbreedsarchitecture.domain


import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.source.local.FavoriteBreedsRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepository
import javax.inject.Inject

class FavCatComparisonUseCase @Inject constructor(
    private val breedsRepository: BreedsRepository,
    private val repositoryFavorite: FavoriteBreedsRepository) {

       private suspend fun combineData(): List<Breed> {
        val localResponse : List<Breed> = repositoryFavorite.readAllData()
        val remoteResponse : List<Breed> = breedsRepository.getDefaultBreeds()

        remoteResponse.forEachIndexed { i, k ->
            localResponse.forEachIndexed { j, l ->
                if (localResponse[j].name.equals(remoteResponse[i].name)) {
                    remoteResponse[i].IsCatliked = true
                }
            }
        }
           remoteResponse.forEachIndexed { i,j ->
               if(remoteResponse[i].IsCatliked == null) {
                   remoteResponse[i].IsCatliked = false
               }
           }

        return remoteResponse
    }

    suspend operator fun invoke(): List<Breed> {
        return combineData()
    }
}