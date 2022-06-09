package com.alife.alife_medifood_android.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _dietmk = MutableLiveData<Boolean>()
    val dietmk : LiveData<Boolean>
        get() = _dietmk

    private val _userinfomk = MutableLiveData<Boolean>()
    val userinfomk : LiveData<Boolean>
        get() = _userinfomk

    fun updateuserinfo(userinfo : Boolean){
        _userinfomk.value = userinfo
    }

    fun updatedietmk(isdiet : Boolean){
        _dietmk.value = isdiet
    }

    init{
        _dietmk.value = false
        _userinfomk.value = false
    }
}