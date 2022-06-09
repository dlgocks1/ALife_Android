package com.alife.alife_medifood_android.ui.calendar.service

import com.alife.alife_medifood_android.ui.start.signup.service.AuthLoginRequest
import com.alife.alife_medifood_android.ui.start.signup.service.signupReponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserDetailInterface {

    // 수정
    @POST("/userinfo/user_detail/modify_user/")
    fun modifyuserDetail(@Header("Authorization") authorization: String,
                         @Body userdetailRequest: UserDetailRequest): Call<UserModifyResponse>
    
    // 초기설정
    @POST("/userinfo/user_detail/")
    fun postuserDetail(@Header("Authorization") authorization: String,
                       @Body userdetailRequest: UserDetailRequest): Call<UserModifyResponse>

    @GET("/userinfo/user_detail/")
    fun getuserDetail(@Header("Authorization") authorization: String) : Call<UserDetailResponse>

}