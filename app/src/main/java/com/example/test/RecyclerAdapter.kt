package com.example.test

import android.content.ClipData.Item
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

class RecyclerAdapter(private val mList:List<SpendClass>,private val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var selectedItemPosition = 0
    var isItemSelected = false
    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
            val profileImage: ImageView = ItemView.findViewById(R.id.profile_image)
            val itemName: TextView = ItemView.findViewById(R.id.ItemName)
            val date: TextView = ItemView.findViewById(R.id.date)
            val spendCost: TextView = ItemView.findViewById(R.id.spendCost)
            val cardView:MaterialCardView = ItemView.findViewById(R.id.cardView)
            val dollarSign:TextView = ItemView.findViewById(R.id.dollar_sign)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = mList[position]
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.profileImage)
        holder.itemName.text = currentItem.ItemName
        holder.date.text = currentItem.Date
        holder.spendCost.text = currentItem.spendCost.toString()
//        holder.itemView.setOnClickListener {
//            selectedItemPosition = position
//            notifyDataSetChanged()
//        }
//        if(selectedItemPosition == position) {
//            holder.cardView.elevation = 15f
//        }
//        else{
//            holder.cardView.setBackgroundColor(Color.parseColor("#ffffff"))
//
//        }
        holder.cardView.setOnClickListener {
            if (currentItem.isSelected==true){
                holder.cardView.background = ContextCompat.getDrawable(context,R.drawable.roundcorner)
                holder.itemName.setTextColor(ContextCompat.getColor(context,R.color.textColor))
                holder.date.setTextColor(ContextCompat.getColor(context,R.color.textColor))
                holder.spendCost.setTextColor(ContextCompat.getColor(context,R.color.red))
                holder.dollarSign.setTextColor(ContextCompat.getColor(context,R.color.red))
                holder.cardView.cardElevation = 4f
                currentItem.isSelected=false
                isItemSelected = false
            }else if(!isItemSelected){
                holder.cardView.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.select_tab))
                holder.itemName.setTextColor(ContextCompat.getColor(context,R.color.white))
                holder.date.setTextColor(ContextCompat.getColor(context,R.color.white))
                holder.spendCost.setTextColor(ContextCompat.getColor(context,R.color.white))
                holder.cardView.cardElevation = 20f
                holder.dollarSign.setTextColor(ContextCompat.getColor(context,R.color.white))
                Log.d("testelse","running")
                currentItem.isSelected=true
                isItemSelected=true
            }
        }
//        holder.cardView.setOnClickListener {
//            if (currentItem.isSelected==true){
//                Log.d("testasd","running")
//                holder.cardView.background = null
//                holder.itemName.setTextColor(ContextCompat.getColor(context,R.color.black))
//                holder.date.setTextColor(ContextCompat.getColor(context,R.color.black))
//                holder.spendCost.setTextColor(ContextCompat.getColor(context,R.color.red))
//                holder.cardView.elevation = 5f
//                currentItem.isSelected=false
//            }else {
//                holder.cardView.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.select_tab))
//                holder.itemName.setTextColor(ContextCompat.getColor(context,R.color.white))
//                holder.date.setTextColor(ContextCompat.getColor(context,R.color.white))
//                holder.spendCost.setTextColor(ContextCompat.getColor(context,R.color.white))
//                Log.d("testelse","running")
//                currentItem.isSelected=true
//                holder.cardView.elevation = 5f
//            }
//        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }
}