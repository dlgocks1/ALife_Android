package com.alife.alife_medifood_android.ui.userInfomk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentSignupBodyBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.start.signup.SignupAcitivity
import com.alife.alife_medifood_android.ui.start.signup.SignupViewModel

class FragmentSignupBody : BaseFragment<FragmentSignupBodyBinding>(R.layout.fragment_signup_body) {

    private val userinfoViewModel: UserinfoViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                UserinfoViewModel() as T
        }).get(UserinfoViewModel::class.java)
    }

    override fun initView() {
//        signupViewModel = ViewModelProvider(requireActivity()).get(SignupViewModel::class.java)
//        binding.signupViewModel = signupViewModel
        binding.loginSignupBodyRadiomanBt.setOnClickListener {
            userinfoViewModel.updategender("M")
        }

        binding.loginSignupBodyRadiowomanBt.setOnClickListener {
            userinfoViewModel.updategender("W")
        }

        binding.signupNextBt.setOnClickListener{
            userinfoViewModel.updateheight(binding.loginSignupHeightTv.text.toString())
            userinfoViewModel.updateweight(binding.loginSignupWeightTv.text.toString())
            (activity as UserinfoAcitivity).nextPage()
        }

        binding.signupBackIv.setOnClickListener((activity as UserinfoAcitivity).buttonListener)
    }
}