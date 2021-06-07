package com.example.wongnaiassignment.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.wongnaiassignment.Model.Coin

class DiffUtilCallBack : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.name == newItem.name && oldItem.description == newItem.description //use fields that is visually seen on the list
    }

}