package com.example.wongnaiassignment.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.wongnaiassignment.Model.Coin

class DiffUtilCallBack : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.name == newItem.name && oldItem.id == newItem.id
    }

}