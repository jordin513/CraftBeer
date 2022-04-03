package com.nidroj.craftbeer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.nidroj.craftbeer.api.PunkApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class BeerRepository(private val api: PunkApi) {

    /**
     * Function to get beers using PunkAPI's built in pagination.
     * The pageSize is set to 15 and will
     *
     * @return  Flow of the paginated set of beers
     */
    fun getBeers() = Pager(
        pagingSourceFactory = { BeerPagingSource(api) },
        config = PagingConfig(pageSize = 15, enablePlaceholders = true, maxSize = 80)
    ).flow
        .catch { exception ->
            Timber.e("error getting beers, ${exception.message}")
            //todo emit db value
        }
        .onEach { beer ->
            //todo store in Room DB maybe?

        }

}