package com.example.catbreedsarchitecture.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.source.local.FavoriteBreedsRepository
import com.example.catbreedsarchitecture.domain.FavCatComparisonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryFavorite: FavoriteBreedsRepository,
    private val favCatComparisonUseCase: FavCatComparisonUseCase) : ViewModel() {

    private val _breeds = MutableStateFlow(HomeUiState(onFavouriteChanged = {id,isFavourited ->
        viewModelScope.launch {
            selectedCat(breeds.value.breedsItems, id!!)?.let {
                if (isFavourited == false){
                    repositoryFavorite.addCat(it)
                }
                else {
                    repositoryFavorite.deleteCat(id)
                }
            }
            handleBreeds()
        }
    }))
    val breeds : StateFlow<HomeUiState> = _breeds.asStateFlow()

    fun handleBreeds() {
        viewModelScope.launch {
            val breedsItems = favCatComparisonUseCase()
            _breeds.update {
                it.copy(breedsItems = breedsItems)
            }
        }
    }

    private fun selectedCat(catList: List<Breed>, callId: String) : Breed?{
        var i = 0
        while (i<catList.size){
            if (catList[i].name.equals(callId)){
                return catList[i]
            }
            i++
        }
        return null
    }
}


data class HomeUiState(
    val breedsItems : List<Breed> = listOf(),
    val onFavouriteChanged : (String?, Boolean?) -> Unit
    )




