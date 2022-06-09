package com.alife.alife_medifood_android.ui.userInfomk

import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentSignupFoodfavorBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.home.dietmk.DietmkActivity

class FragmentSignupFoodfavor : BaseFragment<FragmentSignupFoodfavorBinding>(R.layout.fragment_signup_foodfavor) {
    private lateinit var signupViewModel: UserinfoViewModel

    override fun initView() {
        signupViewModel = ViewModelProvider(requireActivity()).get(UserinfoViewModel::class.java)
        binding.signupBackIv.setOnClickListener((activity as UserinfoAcitivity).buttonListener)
        binding.signupNextBt.setOnClickListener((activity as UserinfoAcitivity).buttonListener)
    }
}