package com.example.wongnaiassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wongnaiassignment.Model.Coins
import com.example.wongnaiassignment.Repository.Repository
import androidx.paging.DataSource
import com.example.wongnaiassignment.DataSource.CoinsListDataSource

//class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return MainViewModel(repository) as T
//    }
//}

//class MainViewModelFactory() : DataSource.Factory<Int,Coins>() {
//
//    private var mutableLiveData: MutableLiveData<CoinsListDataSource>? = null
//
//    init {
//        mutableLiveData = MutableLiveData()
//    }
//
//    override fun create(): DataSource<Int, Coins> {
//        val listDataSource = CoinsListDataSource()
//        mutableLiveData.postValue(listDataSource)
//        return listDataSource
//    }
//
//}