package com.alife.alife_medifood_android.ui.home.dietmk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.Food
import com.alife.alife_medifood_android.data.FoodMainList
import kotlin.math.roundToInt

class DietmkShoppingCartFoodListAdapter : RecyclerView.Adapter<DietmkShoppingCartFoodListAdapter.Viewholder>() {
    private var foodlist: List<Food> = listOf()
    private var listener : DietmkShoppingCartFoodListAdapter.OnClickInterface? = null

    fun setFoods(foods: List<Food>) {
        this.foodlist = foods
        notifyDataSetChanged()
    }

    interface OnClickInterface{
        fun onItemClick(food: Food, check:Boolean)
    }

    fun setOnItemClickListener(listener : OnClickInterface){
        this.listener = listener
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
        private val foodpriceTv = itemView.findViewById<TextView>(R.id.item_shopping_cart_food_price_tv)
        private val foodkcalTv = itemView.findViewById<TextView>(R.id.item_shopping_cart_food_kcal_tv)
        private val foodimgIv = itemView.findViewById<ImageView>(R.id.item_shopping_cart_food_iv)
        private val foodweightTv = itemView.findViewById<TextView>(R.id.item_shopping_cart_food_weight_tv)
        private val foodcb = itemView.findViewById<CheckBox>(R.id.item_shopping_cart_foodlist_cb)

        fun bind(food: Food) {
            foodnameTv.text = food.name
            foodpriceTv.text = "${food.price.toString()}원"
            foodkcalTv.text = "${food.kcal.toFloat().roundToInt()} kcal"
            foodimgIv.setImageResource(food.img)
            foodweightTv.text = "${food.weight}g"

            foodcb.setOnCheckedChangeListener{ buttonView, isChecked ->
                if(isChecked){
                    listener?.onItemClick(food,true)
                }else{
                    listener?.onItemClick(food,false)
                }
            }
        }
    }

}