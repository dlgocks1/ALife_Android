package com.alife.alife_medifood_android.ui.userInfomk

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter

class UserinfoViewModel : ViewModel() {

    private val _gender = MutableLiveData<String>()
    val gender : LiveData<String>
        get() = _gender
    fun updategender(gender : String){
        _gender.value = gender
    }

    private val _exercise = MutableLiveData<String>()
    val exercise : LiveData<String>
        get() = _exercise
    fun updateexercise(item : String){
        _exercise.value = item
    }

    private val _height = MutableLiveData<String>()
    val height : LiveData<String>
        get() = _height
    fun updateheight(item : String){
        _height.value = item
    }

    private val _weight = MutableLiveData<String>()
    val weight : LiveData<String>
        get() = _weight
    fun updateweight(item : String){
        _weight.value = item
    }

    private val _vegan_option = MutableLiveData<String>()
    val vegan_option : LiveData<String>
        get() = _vegan_option
    fun updatevegan_option(item : String){
        _vegan_option.value = item
    }

    private val _allergy = MutableLiveData<String>()
    val allergy : LiveData<String>
        get() = _allergy
    fun updateallergy(item : String){
        _allergy.value = item
    }

    private val _favor_category = MutableLiveData<String>()
    val favor_category : LiveData<String>
        get() = _favor_category
    fun updatfavor_category(item : String){
        _favor_category.value = item
    }

    private val _currentPage = MutableLiveData<Int>()

    val currentPage : LiveData<Int>
        get() = _currentPage

    fun setCurrentPage(position : Int){
        _currentPage.value = position
    }

    fun undoPage(){
        if(currentPage.value != 0){
            _currentPage.value = _currentPage.value?.minus(1)
        }
        Log.d(TAG,currentPage.value.toString())
    }

    init {
        _currentPage.value = 0
    }

    companion object {
        private const val TAG = "SignupViewModel"
    }


}