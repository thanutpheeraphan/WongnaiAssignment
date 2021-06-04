package com.example.wongnaiassignment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.ViewHolder.NormalCoinViewHolder
import com.example.wongnaiassignment.databinding.ItemContainerCoinsBinding
import com.example.wongnaiassignment.utils.DiffUtilCallBack

class NormalCoinAdapter : PagingDataAdapter<Coin, NormalCoinViewHolder>(DiffUtilCallBack()){

    companion object {
        const val VIEW_TYPE = 1111
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalCoinViewHolder {
        return NormalCoinViewHolder(ItemContainerCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: NormalCoinViewHolder, position: Int) {
        holder.bind(data = getItem(position))

    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

}