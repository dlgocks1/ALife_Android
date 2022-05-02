package com.alife.alife_medifood_android.ui.start.signup

import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentSignupFoodcategoryBinding
import com.alife.alife_medifood_android.ui.BaseFragment

class FragmentSignupFoodcategory : BaseFragment<FragmentSignupFoodcategoryBinding>(R.layout.fragment_signup_foodcategory) {
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
//        binding.loginSignupEmailBackIv.setOnClickListener {
//            (activity as SignupAcitivity).undoPage()
//        }
        binding.signupBackIv.setOnClickListener((activity as SignupAcitivity).buttonListener)
        binding.signupNextBt.setOnClickListener((activity as SignupAcitivity).buttonListener)

    }

}