package com.alife.alife_medifood_android.ui.home.dietmk

import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentDietmkFoodfavorBinding
import com.alife.alife_medifood_android.ui.BaseFragment

class FragmentDietmkFoodfavor : BaseFragment<FragmentDietmkFoodfavorBinding>(R.layout.fragment_dietmk_foodfavor) {

    override fun initView() {
        binding.dietmkNextBt.setOnClickListener((activity as DietmkActivity).buttonListener)

    }
}