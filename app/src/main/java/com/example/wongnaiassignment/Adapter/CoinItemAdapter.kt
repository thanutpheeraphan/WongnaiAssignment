package com.example.wongnaiassignment.Adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.wongnaiassignment.Model.Coins
//import com.example.wongnaiassignment.R
//import kotlinx.android.synthetic.main.item_container_coins.view.*
//
//
//class CoinItemAdapter : RecyclerView.Adapter<CoinItemAdapter.MyViewHolder>() {
//
//    private var myList = ArrayList<Coins>()
//
//    inner class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)
//
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): MyViewHolder {
//        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_container_coins,parent,false))
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
////        holder.itemView.coin_image = myList[position].iconUrl
//        holder.itemView.coin_description.text = myList[position].description
//        holder.itemView.coin_name.text = myList[position].name
//
//        val uri = myList[position].iconUrl
//        Glide.with(holder.itemView.context).load(uri).into(holder.itemView.coin_image)
//    }
//
//    override fun getItemCount(): Int {
//        return myList.size
//    }
//
//}