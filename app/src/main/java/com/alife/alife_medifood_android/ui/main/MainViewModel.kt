package com.alife.alife_medifood_android.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alife.alife_medifood_android.data.FoodwithTime

class MainViewModel : ViewModel() {
    // 식단을 만들었는지,
    private val _dietmk = MutableLiveData<Boolean>()
    val dietmk : LiveData<Boolean>
        get() = _dietmk

    private val _foodList = MutableLiveData<List<FoodwithTime>>()
    val foodList : LiveData<List<FoodwithTime>>
        get() = _foodList

    // 유저가 자기 정보 입력했는지,
    private val _userinfomk = MutableLiveData<Boolean>()
    val userinfomk : LiveData<Boolean>
        get() = _userinfomk

    fun updateFoodList(foodList : List<FoodwithTime>){
        _foodList.value = foodList
    }

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