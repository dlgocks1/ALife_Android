package com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice

import com.alife.alife_medifood_android.ui.calendar.service.UserDetailRequest
import com.alife.alife_medifood_android.ui.calendar.service.UserDetailResponse
import com.alife.alife_medifood_android.ui.calendar.service.UserModifyResponse
import retrofit2.Call
import retrofit2.http.*

interface DietmkInterface {
    // 가져오기
    @GET("/userinfo/cut_by_price/")
    fun getrecFood(@Header("Authorization") authorization: String,
                    @Query("price") price: Int
    ): Call<DietmkResponse>

}
