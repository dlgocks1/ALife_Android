package com.alife.alife_medifood_android.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _dietmk = MutableLiveData<Boolean>()

    val dietmk : LiveData<Boolean>
        get() = _dietmk

    fun updatedietmk(isdiet : Boolean){
        _dietmk.value = isdiet
    }
    init{
        _dietmk.value = false
    }
}