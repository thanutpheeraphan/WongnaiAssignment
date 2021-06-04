package com.example.wongnaiassignment.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.ViewHolder.FifthCoinViewHolder
import com.example.wongnaiassignment.ViewHolder.NormalCoinViewHolder
import com.example.wongnaiassignment.databinding.ItemContainerCoinsBinding
import com.example.wongnaiassignment.databinding.ItemContainerFifthCoinBinding
import com.example.wongnaiassignment.utils.DiffUtilCallBack


class CoinsItemAdapter : PagingDataAdapter<Coin, RecyclerView.ViewHolder>(DiffUtilCallBack()){


    companion object {
        const val VIEW_TYPE_ONE = 1111
        const val VIEW_TYPE_TWO = 2222
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            1111 -> (holder as NormalCoinViewHolder).bind(getItem(position))
            else -> { // Note the block
                (holder as FifthCoinViewHolder).bind(getItem(position))
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1111 -> NormalCoinViewHolder(ItemContainerCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> { // Note the block
                FifthCoinViewHolder(ItemContainerFifthCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        val itemId = position +1
        return if(itemId!=0 && (itemId%5==0)){
            VIEW_TYPE_TWO
        } else{
            VIEW_TYPE_ONE
        }
    }


}