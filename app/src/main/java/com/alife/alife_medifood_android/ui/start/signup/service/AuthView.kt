package com.alife.alife_medifood_android.ui.start.signup.service

import com.alife.alife_medifood_android.ui.calendar.service.UserDetailResponse
import com.alife.alife_medifood_android.ui.calendar.service.UserDetailResponseItem
import com.alife.alife_medifood_android.ui.calendar.service.UserModifyResponse
import com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice.DietmkResponseItem

interface SignupView {
    fun onSignupSuccess(user: User)
    fun onSignupFailure(message : String)
}

interface LoginView {
    fun onLoginLoading()
    fun onLoginSuccess(user: User)
    fun onLoginFailure(message : String)
}

interface UserDetailView{
    fun onUserDetailSuccess()
    fun onUserDetailFailure(message : String)
}

interface DietmkView{
    fun onDeitmkLoading()
    fun onDietmkSuccess(foodList:ArrayList<DietmkResponseItem>)
    fun onDietmkFailure(message : String)
}

interface ShoppingView{
    fun onPlistLoading()
    fun onPlistSuccess(foodList:ArrayList<DietmkResponseItem>)
    fun onPlistFailure(message : String)
}