package com.alife.alife_medifood_android.ui.shopping.service

import com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice.DietmkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ShoppingInterface {
    @GET("/userinfo/product/plist/")
    fun getPlist(@Header("Authorization") authorization: String,
                   @QueryMap options : Map<String, String>): Call<DietmkResponse>
}