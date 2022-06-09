package com.alife.alife_medifood_android.ui.home.dietmk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alife.alife_medifood_android.data.Food

class DietmkViewModel : ViewModel() {

    private val _dietmk = MutableLiveData<Boolean>()
    val dietmk : LiveData<Boolean>
        get() = _dietmk

    private val _budget = MutableLiveData<String>()
    val budget : LiveData<String>
        get() = _budget

    private val _nowbudget = MutableLiveData<Int>()
    val nowbudget : LiveData<Int>
        get() = _nowbudget

    private val _nowkcal = MutableLiveData<Int>()
    val nowkcal : LiveData<Int>
        get() = _nowkcal

    private val _nowcarbo = MutableLiveData<Int>()
    val nowcarbo : LiveData<Int>
        get() = _nowcarbo

    private val _nowprotein = MutableLiveData<Int>()
    val nowprotein : LiveData<Int>
        get() = _nowprotein

    private val _nowfat = MutableLiveData<Int>()
    val nowfat : LiveData<Int>
        get() = _nowfat

    fun updatenowcarbo(item : Int){
        _nowcarbo.value = item
    }
    fun updatenowprotein(item : Int){
        _nowprotein.value = item
    }
    fun updatenowfat(item : Int){
        _nowfat.value = item
    }

    fun updatenowkcal(item : Int){
        _nowkcal.value = item
    }

    fun updatenowbudget(item : Int){
        _nowbudget.value = item
    }

    fun updatebudget(item : String){
        _budget.value = item
    }

    fun updatedietmk(isdiet : Boolean){
        _dietmk.value = isdiet
    }


    private val _foodList = MutableLiveData<ArrayList<Food>>()
    val foodList : LiveData<ArrayList<Food>>
        get() = _foodList

    fun addFoodList(food : Food){
        _foodList.value!!.add(food)
    }

    fun removeFoodList(food : Food){
        _foodList.value!!.remove(food)
    }

    init{
        _foodList.value = ArrayList()
        _nowbudget.value = 0
        _nowcarbo.value = 0
        _nowprotein.value = 0
        _nowfat.value = 0
        _nowkcal.value=0
    }

}