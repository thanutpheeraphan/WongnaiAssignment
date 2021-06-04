package com.example.wongnaiassignment.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.wongnaiassignment.Model.Coins

class DiffUtilCallBack : DiffUtil.ItemCallback<Coins>(){
    override fun areItemsTheSame(oldItem: Coins, newItem: Coins): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Coins, newItem: Coins): Boolean {
        return oldItem.name == newItem.name && oldItem.id == newItem.id
    }

}