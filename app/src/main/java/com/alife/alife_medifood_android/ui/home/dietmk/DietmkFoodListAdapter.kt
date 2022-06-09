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
import java.text.DecimalFormat
import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.roundToInt

class DietmkFoodListAdapter : RecyclerView.Adapter<DietmkFoodListAdapter.Viewholder>() {
    private var foodlist: List<Food> = listOf()
    private val decimalFormat: DecimalFormat = DecimalFormat("#,###")
    private var listener : OnClickInterface? = null

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dietmk_foodlist, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(viewHolder: Viewholder, position: Int) {
        viewHolder.bind(foodlist[position])
    }

    override fun getItemCount(): Int {
        return foodlist.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodnameTv = itemView.findViewById<TextView>(R.id.item_dietmk_food_name_tv)
        private val foodkcalTv = itemView.findViewById<TextView>(R.id.item_dietmk_food_kcal_tv)
        private val foodimgIv = itemView.findViewById<ImageView>(R.id.item_dietmk_food_iv)
        private val foodpriceTv = itemView.findViewById<TextView>(R.id.item_dietmk_food_price_tv)
        private val foodcb = itemView.findViewById<CheckBox>(R.id.item_dietmk_foodlist_cb)
        fun bind(food: Food) {
            foodnameTv.text = food.name
            foodkcalTv.text = "${food.kcal} kcal"
            foodimgIv.setImageResource(food.img)
            val price = decimalFormat.format(food.price.toString().replace(",".toRegex(), "").toDouble())
            foodpriceTv.text = "${price}원"

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