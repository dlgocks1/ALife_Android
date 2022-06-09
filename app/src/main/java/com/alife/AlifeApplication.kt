package com.alife

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class AlifeApplication : Application(){

    companion object{
        lateinit var useremail : String
        lateinit var password : String

        private lateinit var alifeApplication : AlifeApplication
        fun getInstance() : AlifeApplication = alifeApplication
    }

    override fun onCreate() {
        super.onCreate()
        alifeApplication = this

    }
}