package com.alife.alife_medifood_android.ui.start.signup

import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentSignupBodyBinding
import com.alife.alife_medifood_android.ui.BaseFragment

class FragmentSignupBody : BaseFragment<FragmentSignupBodyBinding>(R.layout.fragment_signup_body) {
    private lateinit var signupViewModel: SignupViewModel

    override fun initView() {
        signupViewModel = ViewModelProvider(requireActivity()).get(SignupViewModel::class.java)
        binding.signupViewModel = signupViewModel
//        binding.signupNextBt.setOnClickListener {
//            signupViewModel.setCurrentPage(1)
//        }

        binding.signupNextBt.setOnClickListener((activity as SignupAcitivity).buttonListener)
        binding.signupBackIv.setOnClickListener((activity as SignupAcitivity).buttonListener)
    }
}