package com.nidroj.craftbeer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nidroj.craftbeer.data.BeerRepository
import com.nidroj.craftbeer.data.model.Beer
import kotlinx.coroutines.flow.Flow


class BeerViewModel(private val beerRepository: BeerRepository) : ViewModel() {

    /**
     * Function to get beers from repository and cache them in the viewModelScope
     *
     * @return  Flow of the paginated set of beers
     */
    fun getBeers(): Flow<PagingData<Beer>> {
        return beerRepository.getBeers().cachedIn(viewModelScope)
    }
}


