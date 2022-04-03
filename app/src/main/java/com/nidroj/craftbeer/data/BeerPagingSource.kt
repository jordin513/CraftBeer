package com.nidroj.craftbeer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nidroj.craftbeer.api.PunkApi
import com.nidroj.craftbeer.data.model.Beer
import timber.log.Timber

/**
 * Automatically loads pages of data supplied by PunkAPI.
 * Does so as needed, i.e. while scrolling through a RecyclerView.
 * Stores the data in memory cache.
 *
 * @property api PunkAPI instance
 */
class BeerPagingSource(
    private val api: PunkApi
) : PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val page = params.key ?: 1

            val response = api.getBeers(page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }
}