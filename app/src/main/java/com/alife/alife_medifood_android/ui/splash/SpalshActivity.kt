package com.alife.alife_medifood_android.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.ActivitySplashBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.ui.main.MainActivity

class SpalshActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
        }, 1000)
    }

}