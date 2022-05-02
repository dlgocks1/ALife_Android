package com.alife.alife_medifood_android.ui.start.signup

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentSignupEmailBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.main.MainActivity

class FragmentSignupEmail :
    BaseFragment<FragmentSignupEmailBinding>(R.layout.fragment_signup_email) {

    private lateinit var signupViewModel: SignupViewModel

//    private val activityViewModel: MainViewModel by lazy {
//        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
//                MainViewModel() as T
//        }).get(MainViewModel::class.java)
//    }

    override fun initView() {
        signupViewModel = ViewModelProvider(requireActivity()).get(SignupViewModel::class.java)
        binding.signupViewModel = signupViewModel

        binding.signupNextBt.setOnClickListener{
            signupViewModel.setemail(binding.loginSignupEmailEdittv.text.toString())
            signupViewModel.setpasswrod(binding.loginSignupPasswdEdittv.text.toString())
            (activity as SignupAcitivity).nextPage()
        }

        binding.signupBackIv.setOnClickListener((activity as SignupAcitivity).buttonListener)
    }
}