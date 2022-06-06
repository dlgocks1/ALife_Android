package com.alife.alife_medifood_android.ui.home.dietmk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.Food
import com.alife.alife_medifood_android.data.FoodMainList

class DietmkShoppingCartFoodListAdapter : RecyclerView.Adapter<DietmkShoppingCartFoodListAdapter.Viewholder>() {
    private var foodlist: List<Food> = listOf()

    fun setFoods(foods: List<Food>) {
        this.foodlist = foods
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_cart_food, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(viewHolder: Viewholder, position: Int) {
        viewHolder.bind(foodlist[position])
    }

    override fun getItemCount(): Int {
        return foodlist.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodnameTv = itemView.findViewById<TextView>(R.id.item_shopping_cart_food_name_tv)
        private val foodkcalTv = itemView.findViewById<TextView>(R.id.item_shopping_cart_food_kcal_tv)
        private val foodimgIv = itemView.findViewById<ImageView>(R.id.item_shopping_cart_food_iv)
        private val foodweightTv = itemView.findViewById<TextView>(R.id.item_shopping_cart_food_weight_tv)
        fun bind(food: Food) {
            foodnameTv.text = food.name
            foodkcalTv.text = food.kcal
            foodimgIv.setImageResource(food.img)
            foodweightTv.text = "${food.weight}g"
        }
    }

}