package com.alife.alife_medifood_android.ui.start.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.alife.AlifeApplication
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.ActivityLoginBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.ui.main.MainActivity
import com.alife.alife_medifood_android.ui.start.signup.SignupAcitivity
import com.alife.alife_medifood_android.ui.start.signup.service.AuthLoginRequest
import com.alife.alife_medifood_android.ui.start.signup.service.AuthService
import com.alife.alife_medifood_android.ui.start.signup.service.LoginView
import com.alife.alife_medifood_android.ui.start.signup.service.User

class LoginActivity:BaseActivity<ActivityLoginBinding>(R.layout.activity_login), LoginView {

    lateinit var authService: AuthService
    override fun initView() {
        authService = AuthService()
        authService.setloginView(this)
    }
    override fun initListener() {
        val onClickListener = View.OnClickListener {
            when(it.id){
                R.id.login_bt -> authService.login(AuthLoginRequest(binding.loginEmailEdittv.text.toString(),binding.loginPasswdEdittv.text.toString()))
                R.id.login_signup_tv -> startActivity(Intent(this,SignupAcitivity::class.java))
            }
        }
        binding.loginSignupTv.setOnClickListener(onClickListener)
        binding.loginBt.setOnClickListener(onClickListener)
    }

    override fun onLoginLoading() {
        binding.loadingProgressBar.visibility=View.VISIBLE
    }

    override fun onLoginSuccess(user: User) {
        AlifeApplication.useremail = binding.loginEmailEdittv.text.toString()
        binding.loadingProgressBar.visibility=View.GONE
        AlifeApplication.password = binding.loginPasswdEdittv.text.toString()
        startActivityWithClear(MainActivity::class.java)
    }

    override fun onLoginFailure(message: String) {
        binding.loadingProgressBar.visibility=View.GONE
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}