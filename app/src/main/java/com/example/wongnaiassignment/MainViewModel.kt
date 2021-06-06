package com.example.wongnaiassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wongnaiassignment.DataSource.CoinsListDataSource
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.Network.RetroService
import com.example.wongnaiassignment.Network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    var retroService: RetroService = RetrofitInstance.api

    fun getListData(query: String? = null, sort: String? = null): Flow<PagingData<Coin>> {
        return Pager(config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = { CoinsListDataSource(retroService, query, sort) }).flow.cachedIn(
            viewModelScope
        )
    }
}