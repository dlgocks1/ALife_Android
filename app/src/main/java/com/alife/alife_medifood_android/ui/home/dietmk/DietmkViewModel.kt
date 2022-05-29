package com.alife.alife_medifood_android.ui.home.dietmk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DietmkViewModel : ViewModel() {
    // 식단 일수, favor, 예산등을 받아와서 저장
    private val _dietmk = MutableLiveData<Boolean>()

    val dietmk : LiveData<Boolean>
        get() = _dietmk

    fun updatedietmk(isdiet : Boolean){
        _dietmk.value = isdiet
    }

}