package com.example.wongnaiassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wongnaiassignment.DataSource.CoinsListDataSource
import com.example.wongnaiassignment.Model.Coins
import com.example.wongnaiassignment.Repository.Repository
import com.example.wongnaiassignment.Repository.RetroService
import com.example.wongnaiassignment.Repository.RetrofitInstance
import kotlinx.coroutines.flow.Flow

//class MainViewModel(private val repository: Repository): ViewModel() {
//
//    val myResponse: MutableLiveData<Response<ResponseData>> = MutableLiveData()
//
//    val restaurantByIdResponse: MutableLiveData<Response<ResponseData>> = MutableLiveData()
//
//    fun getRestaurantModelById(id: Int){
//        viewModelScope.launch{
//            val response = repository.getCoinsByLimitPage(id)
//            restaurantByIdResponse.value = response
//        }
//    }
//}

class MainViewModel(): ViewModel() {

 lateinit var retroService: RetroService

 init {
     retroService = RetrofitInstance.api
 }

    fun getListData(): Flow<PagingData<Coins>> {
        return Pager(config = PagingConfig(pageSize = 10,maxSize = 100),
        pagingSourceFactory = {CoinsListDataSource(retroService)}).flow.cachedIn(viewModelScope)
    }
}