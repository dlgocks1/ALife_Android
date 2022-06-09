package com.alife.alife_medifood_android.ui.userInfomk

import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentSignupExerciseBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.start.signup.SignupAcitivity
import com.alife.alife_medifood_android.ui.start.signup.SignupViewModel

class FragmentSignupExercise : BaseFragment<FragmentSignupExerciseBinding>(R.layout.fragment_signup_exercise) {
    private lateinit var signupViewModel: UserinfoViewModel

//    private val activityViewModel: MainViewModel by lazy {
//        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
//                MainViewModel() as T
//        }).get(MainViewModel::class.java)
//    }

    override fun initView() {
        signupViewModel = ViewModelProvider(requireActivity()).get(UserinfoViewModel::class.java)

        binding.signupExerciseHighCb.setOnClickListener {
            signupViewModel.updateexercise("high")
        }
        binding.signupExerciseMediumCb.setOnClickListener {
            signupViewModel.updateexercise("middle")
        }
        binding.signupExerciseLowCb.setOnClickListener {
            signupViewModel.updateexercise("low")
        }

        binding.signupNextBt.setOnClickListener{
            (activity as UserinfoAcitivity).nextPage()
        }

        binding.signupBackIv.setOnClickListener((activity as UserinfoAcitivity).buttonListener)

    }
}