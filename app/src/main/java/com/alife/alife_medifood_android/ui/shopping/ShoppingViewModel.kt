package com.alife.alife_medifood_android.ui.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppingViewModel : ViewModel() {
    private val _searchStr = MutableLiveData<String>()
    val searchStr : LiveData<String>
        get() = _searchStr

    init{
        _searchStr.value = ""
    }
    fun setSearchStr(str : String){
        _searchStr.value = str
    }
}