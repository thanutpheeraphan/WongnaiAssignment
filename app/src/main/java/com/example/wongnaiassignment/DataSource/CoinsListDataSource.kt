package com.example.wongnaiassignment.DataSource

import android.util.Log
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.Network.RetroService
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.lang.Exception

class CoinsListDataSource(val api: RetroService) : PagingSource<Int, Coin>() {

    companion object {
        private const val FIRST_PAGE_INDEX = 0
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
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            Log.d("NEXTKEY", nextPage.toString())
            val response = api.getDataWithLimit(offset = nextPage, limit = PAGE_LIMIT)
            Log.d("RESPONSE", response.data.coins[0].name.toString())

            // Since 0 is the lowest page number, return null to signify no more pages should
            // be loaded before it.
            val prevKey = if (nextPage > 0) nextPage - 10 else null

            Log.d("PREVKEY", nextPage.toString())

            // This API defines that it's out of data when a page returns empty. When out of
            // data, we return `null` to signify no more pages should be loaded
            val nextKey = if (response.data.coins.isNotEmpty()) nextPage + 10 else null
            LoadResult.Page(
                data = response.data.coins,
//                prevKey = prevKey,
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