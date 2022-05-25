package com.example.catbreedsarchitecture.ui.home

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.Items
import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepository
import com.example.catbreedsarchitecture.domain.FavCatComparisonUseCase
import com.example.catbreedsarchitecture.util.downloadFromUrl
import com.example.catbreedsarchitecture.util.placeholderProgressBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BreedsRepository,
    private val localRepository: BreedsLocalRepository ,
    private val favCatComparisonUseCase: FavCatComparisonUseCase) : ViewModel() {

    private val _breeds = MutableStateFlow(HomeUiState(onFavouriteChanged = {id,isFavourited ->
        Log.d("callback",id+" - "+isFavourited.toString())

        viewModelScope.launch {
            selectedCat(breeds.value.breedsItems, id!!)?.let {
                if (isFavourited == false){
                    localRepository.addCat(it)
                }
                else if (isFavourited == true){
                    localRepository.deleteCat(id)
                }
            }
            handleBreeds()
        }
    }))
    val breeds : StateFlow<HomeUiState> = _breeds.asStateFlow()

    fun handleBreeds() {
        viewModelScope.launch {
            //******************
            //artık veriler lokaldeki verilerle karşılaştırılıp UI'a verilecek
            // val breedsItems = repository.getDefaultBreeds()
            //******************
            val breedsItems = favCatComparisonUseCase()
            _breeds.update {
                it.copy(breedsItems = breedsItems)
            }
        }
    }

    fun selectedCat(catList: List<Breed>, callId: String) : Breed?{
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

    ) {

}




