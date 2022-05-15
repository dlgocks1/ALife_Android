package com.alife.alife_medifood_android.ui.main.home

import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentHomeBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.main.MainViewModel

class FragmentHome : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home){
    private lateinit var mainViewModel : MainViewModel

    override fun initView() {
//        binding.lifecycleOwner = this
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.mainViewModel = mainViewModel

        // 식단이 작성되어 있으면 변경
        if(mainViewModel.dietmk.value == true){
            fragmentManager!!.beginTransaction().replace(R.id.home_frameLayout, FragmentHomeCalories()).commit()
        }else{
            fragmentManager!!.beginTransaction().replace(R.id.home_frameLayout, FragmentHomeEmpty()).commit()
        }

    }
}