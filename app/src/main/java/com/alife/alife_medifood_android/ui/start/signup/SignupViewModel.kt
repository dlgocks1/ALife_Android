package com.alife.alife_medifood_android.ui.start.signup

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter

class SignupViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _adapter = MutableLiveData<SignupAcitivity.PagerAdapter>()
    private val _currentPage = MutableLiveData<Int>()

    val email : LiveData<String>
        get() = _email
    val password : LiveData<String>
        get() = _password
    val adapter : LiveData<SignupAcitivity.PagerAdapter>
        get() = _adapter

    val currentPage : LiveData<Int>
        get() = _currentPage

    fun setemail(email : String){_email.value = email}
    fun setpasswrod(password : String){_password.value = password}

    fun setCurrentPage(position : Int){
        _currentPage.value = position
    }

    fun setAdapter(adapter: SignupAcitivity.PagerAdapter){
        _adapter.value = adapter
    }

    fun undoPage(){
        if(currentPage.value != 0){
            _currentPage.value = _currentPage.value?.minus(1)
        }
        Log.d(TAG,currentPage.value.toString())
    }

    init {
        _email.value = ""
        _adapter.value = null
        _currentPage.value = 0
    }

    companion object {
        private const val TAG = "SignupViewModel"
    }


}