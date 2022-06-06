package com.alife.alife_medifood_android.ui.home.dietmk

import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.Food
import com.alife.alife_medifood_android.databinding.FragmentDietmkShoppingCartBinding
import com.alife.alife_medifood_android.ui.BaseFragment

class FragmentDietmkShoppingCart : BaseFragment<FragmentDietmkShoppingCartBinding>(R.layout.fragment_dietmk_shopping_cart) {
    override fun initView() {

        val dietmkFoodListAdapter = DietmkShoppingCartFoodListAdapter()
        dietmkFoodListAdapter.setFoods(listOf(
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,3000,124),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,4000,125),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,5000,125),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,5000,325),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,5000,502)
        ))
        binding.dietmkShoppingCartRv.adapter = dietmkFoodListAdapter
        binding.dietmkShoppingCartPurchaseBt.setOnClickListener{
            (activity as DietmkActivity).nextPage()
        }

    }
}