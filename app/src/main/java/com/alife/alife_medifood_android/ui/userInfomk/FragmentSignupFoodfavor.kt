package com.alife.alife_medifood_android.ui.userInfomk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentSignupFoodfavorBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.home.dietmk.DietmkActivity
import com.alife.alife_medifood_android.ui.home.dietmk.DietmkViewModel
import java.text.DecimalFormat

class FragmentSignupFoodfavor(val type : String) : BaseFragment<FragmentSignupFoodfavorBinding>(R.layout.fragment_signup_foodfavor) {
    private lateinit var signupViewModel: UserinfoViewModel

    private val dietmkViewModel: DietmkViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                DietmkViewModel() as T
        }).get(DietmkViewModel::class.java)
    }

    override fun initView() {
        if(type=="Signup"){
            signupViewModel = ViewModelProvider(requireActivity()).get(UserinfoViewModel::class.java)
            binding.signupBackIv.setOnClickListener((activity as UserinfoAcitivity).buttonListener)
            binding.signupNextBt.setOnClickListener((activity as UserinfoAcitivity).buttonListener)
        }
        if(type=="Dietmk"){
            val options = mutableMapOf<String,String>()

            // 고단, 저탄, 저지방, 저염, 저열량
            binding.dietmkFoodfavorCb1.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    options += Pair("high_protein","a")
                }else{
                    options.remove("high_protein")
                }
            }
            binding.dietmkFoodfavorCb2.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    options += Pair("low_carbo","a")
                }else{
                    options.remove("low_carbo")
                }
            }
            binding.dietmkFoodfavorCb3.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    options += Pair("low_fat","a")
                }else{
                    options.remove("low_fat")
                }
            }
            binding.dietmkFoodfavorCb4.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    options += Pair("low_salt","a")
                }else{
                    options.remove("low_salt")
                }
            }
            binding.dietmkFoodfavorCb5.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    options += Pair("low_calory","a")
                }else{
                    options.remove("low_calory")
                }
            }
            binding.signupNextBt.setOnClickListener {
                (activity as DietmkActivity).nextPage()
                dietmkViewModel.updateKeyword(options)
            }

        }
    }

}