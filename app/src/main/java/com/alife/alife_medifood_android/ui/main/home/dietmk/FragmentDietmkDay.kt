package com.alife.alife_medifood_android.ui.main.home.dietmk

import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentDietmkDayBinding
import com.alife.alife_medifood_android.ui.BaseFragment

class FragmentDietmkDay : BaseFragment<FragmentDietmkDayBinding>(R.layout.fragment_dietmk_day) {

    override fun initView() {
        binding.dietmkNextBt.setOnClickListener((activity as DietmkActivity).buttonListener)
    }
}