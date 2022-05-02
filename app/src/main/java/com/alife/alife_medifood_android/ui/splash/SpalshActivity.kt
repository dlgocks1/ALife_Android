package com.alife.alife_medifood_android.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.ActivitySplashBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.ui.start.login.LoginActivity

class SpalshActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivityWithClear(LoginActivity::class.java)
        }, 1000)
    }

}