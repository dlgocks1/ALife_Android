package com.alife.alife_medifood_android.ui.start.login

import android.content.Intent
import android.view.View
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.ActivityLoginBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.ui.main.MainActivity
import com.alife.alife_medifood_android.ui.start.signup.SignupAcitivity

class LoginActivity:BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun initView() {

    }
    override fun initListener() {

        val onClickListener = View.OnClickListener {
            when(it.id){
                R.id.login_bt -> startActivityWithClear(MainActivity::class.java)
                R.id.login_signup_tv -> startActivity(Intent(this,SignupAcitivity::class.java))
            }
        }
        binding.loginSignupTv.setOnClickListener(onClickListener)
        binding.loginBt.setOnClickListener(onClickListener)
    }
}