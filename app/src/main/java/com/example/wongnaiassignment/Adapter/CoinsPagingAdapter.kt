package com.example.wongnaiassignment.Adapter

import android.net.Uri
import android.text.Html
import android.text.Html.fromHtml
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.R
import com.example.wongnaiassignment.databinding.ItemContainerCoinsBinding
import com.example.wongnaiassignment.databinding.ItemContainerFifthCoinBinding
import com.example.wongnaiassignment.utils.DiffUtilCallBack
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


class CoinsPagingAdapter : PagingDataAdapter<Coin, CoinsPagingAdapter.MyViewHolder>(DiffUtilCallBack()){


    inner class MyViewHolder(val binding: ItemContainerCoinsBinding): RecyclerView.ViewHolder(binding.root){}




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsPagingAdapter.MyViewHolder {
        return MyViewHolder(ItemContainerCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CoinsPagingAdapter.MyViewHolder, position: Int) {
        Log.d("TAG", position.toString())
        val item = getItem(position) //can put !! to remove ? from all uses of item
        holder.binding.coinName.text = item?.name

        if (item?.description!=null){ //this if is used to remove the <p> (html tags) from the description received from the api
            val spanned = fromHtml(item?.description)
            val chars = CharArray(spanned.length)
            TextUtils.getChars(spanned, 0, spanned.length, chars, 0)
            val plainText = String(chars)
            holder.binding.coinDescription.text = plainText
        }else{holder.binding.coinDescription.text = null}

        val uri =item?.iconUrl
        if (uri != null) {
            if(uri.endsWith(".svg")){
                GlideToVectorYou.init().with(holder.itemView.context).setPlaceHolder(
                        R.drawable.ic_launcher_background,
                        R.drawable.ic_launcher_background
                ).load(
                        Uri.parse(uri), holder.binding.coinImage)

            }else{
                Glide.with(holder.itemView.context).load(uri).into(holder.binding.coinImage)

            }
        }



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