package com.example.wongnaiassignment.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wongnaiassignment.Model.Coins
import com.example.wongnaiassignment.R
import com.example.wongnaiassignment.databinding.ItemContainerCoinsBinding
import com.example.wongnaiassignment.utils.DiffUtilCallBack

class CoinsPagingAdapter : PagingDataAdapter <Coins,CoinsPagingAdapter.MyViewHolder>(DiffUtilCallBack()){


    inner class MyViewHolder(val binding: ItemContainerCoinsBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(ItemContainerCoinsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = getItem(position)
        holder.binding.coinName.text = item?.name
        holder.binding.coinDescription.text = item?.description
        val uri =item?.iconUrl
        Glide.with(holder.itemView.context).load(uri).into(holder.binding.coinImage)




//        with(holder){
//            val item = getItem(position)
//            if (item != null) {
//                Log.d("TAG", item.name.toString())
//            }
//            else{
//                Log.d("TAG", "wtf is going on ")
//            }
//            binding.coinName.text = item?.name
//            binding.coinDescription.text = item?.description
//            Log.d("TAG", item?.name.toString())
//
//            val uri =item?.iconUrl
//            Glide.with(itemView.context).load(uri).into(binding.coinImage)
//        }
    }

}