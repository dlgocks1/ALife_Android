package com.alife.alife_medifood_android.ui.shopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.FoodMainList
import com.alife.alife_medifood_android.data.FoodSearchList
import com.bumptech.glide.Glide

class SearchFoodListAdapter : RecyclerView.Adapter<SearchFoodListAdapter.Viewholder>() {
    private var foodlist: List<FoodSearchList> = listOf()

    fun setFoods(foods: List<FoodSearchList>) {
        this.foodlist = foods
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_food, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(viewHolder: Viewholder, position: Int) {
        viewHolder.bind(foodlist[position])
    }

    override fun getItemCount(): Int {
        return foodlist.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodimgIv = itemView.findViewById<ImageView>(R.id.item_search_iv)
        private val nameTv = itemView.findViewById<TextView>(R.id.item_search_name_tv)
        private val priceTv = itemView.findViewById<TextView>(R.id.item_search_price_tv)
        private val type1Tv = itemView.findViewById<TextView>(R.id.item_search_type1)
        private val type2Tv = itemView.findViewById<TextView>(R.id.item_search_type2)

        fun bind(food: FoodSearchList) {
            nameTv.text = food.name
            priceTv.text = "${food.price} 원"
            type1Tv.text = food.tagList[0]
            type2Tv.text = food.tagList[1]
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