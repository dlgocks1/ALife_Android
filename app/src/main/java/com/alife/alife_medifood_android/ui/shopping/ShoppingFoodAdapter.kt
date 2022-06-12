package com.alife.alife_medifood_android.ui.shopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.FoodMainList
import com.bumptech.glide.Glide

class ShoppingFoodAdapter : RecyclerView.Adapter<ShoppingFoodAdapter.Viewholder>() {
    private var foodlist: List<FoodMainList> = listOf()

    fun setFoods(foods: List<FoodMainList>) {
        this.foodlist = foods
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_main, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(viewHolder: Viewholder, position: Int) {
        viewHolder.bind(foodlist[position])
    }

    override fun getItemCount(): Int {
        return foodlist.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodimgIv = itemView.findViewById<ImageView>(R.id.item_shopping_iv)
        private val nameTv = itemView.findViewById<TextView>(R.id.item_shopping_name_tv)
        private val priceTv = itemView.findViewById<TextView>(R.id.item_shopping_price_tv)
        fun bind(food: FoodMainList) {
            nameTv.text = food.name
            priceTv.text = "${food.price} 원"
//            foodimgIv.setImageResource(food.img)
            Glide
                .with(itemView)
                .load(food.img)
                .centerCrop()
                .placeholder(R.drawable.img_ready)
                .into(foodimgIv)
        }
    }

}