package com.alife.alife_medifood_android.ui.home.dietmk

import android.view.View
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.Food
import com.alife.alife_medifood_android.databinding.FragmentDietmkSelectFoodBinding
import com.alife.alife_medifood_android.ui.BaseFragment

class FragmentDietmkSelectFood : BaseFragment<FragmentDietmkSelectFoodBinding>(R.layout.fragment_dietmk_select_food) {
    override fun initView() {
        val dietmkFoodListAdapter = DietmkFoodListAdapter()
        dietmkFoodListAdapter.setFoods(listOf(
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,3000),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,4000),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,5000),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,5000),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food,5000)
        ))
        binding.dietmkFoodRv.adapter = dietmkFoodListAdapter
        binding.dietmkFoodRv2.adapter = dietmkFoodListAdapter

        binding.dietmkNextLa.setOnClickListener { (activity as DietmkActivity).nextPage() }

    }
}