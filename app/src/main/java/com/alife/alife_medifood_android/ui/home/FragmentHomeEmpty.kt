package com.alife.alife_medifood_android.ui.home

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentHomeEmptyBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.home.dietmk.DietmkActivity
import com.alife.alife_medifood_android.ui.main.MainViewModel
import com.alife.alife_medifood_android.ui.userInfomk.UserinfoAcitivity

class FragmentHomeEmpty : BaseFragment<FragmentHomeEmptyBinding>(R.layout.fragment_home_empty){
    private lateinit var mainViewModel : MainViewModel

    override fun initView() {
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.homeStarttoregisterBt.setOnClickListener {
            startActivity(Intent(requireContext(), DietmkActivity::class.java))
        }
    }
}