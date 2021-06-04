package com.example.wongnaiassignment.ViewHolder

import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.R
import com.example.wongnaiassignment.databinding.ItemContainerFifthCoinBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class FifthCoinViewHolder (private val binding: ItemContainerFifthCoinBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(data: Coin?){
//        Log.d("TAG", "OnBindViewHolder")
        binding.coinName.text = data?.name

        val uri = data?.iconUrl
        if (uri != null) {
            if(uri.endsWith(".svg")){
                GlideToVectorYou.init().with(itemView.context).setPlaceHolder(
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background
                ).load(
                    Uri.parse(uri), binding.coinImage)

            }else{
                Glide.with(itemView.context).load(uri).into(binding.coinImage)

            }
        }
    }
}