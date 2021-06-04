package com.example.wongnaiassignment.DataSource

import android.util.Log
import com.example.wongnaiassignment.Model.Coins
import com.example.wongnaiassignment.Repository.RetroService
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.lang.Exception

class CoinsListDataSource(val api: RetroService): PagingSource<Int,Coins>(){

    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }
    override fun getRefreshKey(state: PagingState<Int, Coins>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coins> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = api.getDataWithLimit(nextPage)
            Log.d("TAG",response.coins.size.toString())

            // Since 0 is the lowest page number, return null to signify no more pages should
            // be loaded before it.
//            val prevKey = if (nextPage > 1) nextPage - 1 else null

            // This API defines that it's out of data when a page returns empty. When out of
            // data, we return `null` to signify no more pages should be loaded
            val nextKey = if (response.coins.isNotEmpty()) nextPage + 1 else null
            LoadResult.Page(
                data = response.coins,
//                prevKey = prevKey,
                prevKey = null,
                nextKey = nextKey
            )

        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
        catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}