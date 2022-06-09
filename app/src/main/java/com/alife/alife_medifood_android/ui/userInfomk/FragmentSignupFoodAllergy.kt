package com.alife.alife_medifood_android.ui.userInfomk

import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentSignupFoodallergyBinding
import com.alife.alife_medifood_android.databinding.FragmentSignupFoodcategoryBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.start.signup.SignupAcitivity

class FragmentSignupFoodAllergy : BaseFragment<FragmentSignupFoodallergyBinding>(R.layout.fragment_signup_foodallergy) {
    private lateinit var signupViewModel: UserinfoViewModel

    override fun initView() {
        signupViewModel = ViewModelProvider(requireActivity()).get(UserinfoViewModel::class.java)

        binding.loginSignupFoodcategoryCb1.setOnClickListener {
            signupViewModel.updateallergy("토마토")
        }
        binding.loginSignupFoodcategoryCb2.setOnClickListener {
            signupViewModel.updateallergy("우유")
        }

        binding.signupBackIv.setOnClickListener((activity as UserinfoAcitivity).buttonListener)
        binding.signupNextBt.setOnClickListener((activity as UserinfoAcitivity).buttonListener)
    }

}