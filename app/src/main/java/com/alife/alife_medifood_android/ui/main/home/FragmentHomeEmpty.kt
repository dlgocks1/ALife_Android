package com.alife.alife_medifood_android.ui.main.home

import android.content.Intent
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentHomeEmptyBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.main.home.dietmk.DietmkActivity

class FragmentHomeEmpty : BaseFragment<FragmentHomeEmptyBinding>(R.layout.fragment_home_empty){
    override fun initView() {
        binding.homeStarttoregisterBt.setOnClickListener {
            startActivity(Intent(requireContext(),DietmkActivity::class.java))
        }
    }
}