package com.example.wongnaiassignment.ViewHolder

import android.net.Uri
import android.text.Html
import android.text.TextUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wongnaiassignment.Model.Coin
import com.example.wongnaiassignment.R
import com.example.wongnaiassignment.databinding.ItemContainerCoinsBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class StandardViewHolder(private val binding: ItemContainerCoinsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Coin?) {
        binding.coinName.text = data?.name

        if (data?.description != null) { //this is used to remove the <p> (html tags) from the description received from the api
            val spanned = Html.fromHtml(data?.description)
            val chars = CharArray(spanned.length)
            TextUtils.getChars(spanned, 0, spanned.length, chars, 0)
            val plainText = String(chars)
            binding.coinDescription.text = plainText
        } else {
            binding.coinDescription.text = null
        }

        val uri = data?.iconUrl
        if (uri != null) {
            if (uri.endsWith(".svg")) {
                GlideToVectorYou.init().with(itemView.context).setPlaceHolder(
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background
                ).load(
                    Uri.parse(uri), binding.coinImage
                )

            } else {
                Glide.with(itemView.context).load(uri).into(binding.coinImage)

            }
        }

    }
}