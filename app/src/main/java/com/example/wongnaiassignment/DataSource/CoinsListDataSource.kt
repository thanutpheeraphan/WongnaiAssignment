package com.example.wongnaiassignment.DataSource

import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.Network.RetroService
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.lang.Exception

class CoinsListDataSource(
    private val api: RetroService,
    private val query: String? = null,
    private val sort: String? = null
) : PagingSource<Int, Coin>() {

    companion object {
        private const val FIRST_PAGE_OFFSET = 0
        private const val PAGE_LIMIT = 10

    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(10)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(10)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_OFFSET
            val response = api.getDataWithParams(
                offset = nextPage,
                limit = PAGE_LIMIT,
                prefix = query,
                sort = sort
            )

            // Since 0 is the lowest offset, return null to signify no more pages should
            // be loaded before it.
            val prevKey = if (nextPage > 0) nextPage - 10 else null


            // This API defines that it's out of data when a page returns empty. When out of
            // data, we return `null` to signify no more pages should be loaded
            val nextKey = if (response.data.coins.size == PAGE_LIMIT) nextPage + 10 else null

            LoadResult.Page(
                data = response.data.coins,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


}