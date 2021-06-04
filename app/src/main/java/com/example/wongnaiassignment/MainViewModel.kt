package com.example.wongnaiassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wongnaiassignment.DataSource.CoinsListDataSource
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.Repository.RetroService
import com.example.wongnaiassignment.Repository.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {

 var retroService: RetroService = RetrofitInstance.api

    fun getListData(): Flow<PagingData<Coin>> {
        return Pager(config = PagingConfig(pageSize = 10,maxSize = 100),
        pagingSourceFactory = {CoinsListDataSource(retroService)}).flow.cachedIn(viewModelScope)
    }
}