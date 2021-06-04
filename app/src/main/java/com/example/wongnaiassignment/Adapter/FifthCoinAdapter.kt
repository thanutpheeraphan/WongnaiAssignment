package com.example.wongnaiassignment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.ViewHolder.FifthCoinViewHolder
import com.example.wongnaiassignment.databinding.ItemContainerFifthCoinBinding
import com.example.wongnaiassignment.utils.DiffUtilCallBack

class FifthCoinAdapter : PagingDataAdapter<Coin, FifthCoinViewHolder>(DiffUtilCallBack()){

    companion object {
        const val VIEW_TYPE_TWO = 2222
    }

    override fun onBindViewHolder(holder: FifthCoinViewHolder, position: Int) {
        holder.bind(data = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FifthCoinViewHolder {
        return FifthCoinViewHolder(ItemContainerFifthCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_TWO
    }
}