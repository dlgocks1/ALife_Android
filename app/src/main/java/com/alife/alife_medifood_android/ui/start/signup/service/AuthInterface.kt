package com.alife.alife_medifood_android.ui.start.signup.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthInterface {
    @POST("/accounts/v1/registration/")
    fun signup(@Body requestbody: AuthRequest): Call<signupReponse>

    @POST("/accounts/v1/login/")
    fun login(@Body requestbody: AuthLoginRequest): Call<signupReponse>
}