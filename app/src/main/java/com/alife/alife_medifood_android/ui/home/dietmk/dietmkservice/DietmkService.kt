package com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice

import android.util.Log
import com.alife.alife_medifood_android.getReposit
import com.alife.alife_medifood_android.ui.home.dietmk.FragmentDietmkSelectFood
import com.alife.alife_medifood_android.ui.start.signup.service.DietmkView
import com.google.gson.Gson
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class DietmkService {

    private lateinit var dietmkView: DietmkView
    private val gson = Gson()

    fun setdietmkView(dietmkView: DietmkView) {
        this.dietmkView = dietmkView
    }

    fun getRecFood(price : Int,options : Map<String,String>) {
        val service = getReposit().create(DietmkInterface::class.java)
        val basic = Credentials.basic("hc2@naver.com", "testtest!")
        dietmkView.onDeitmkLoading()
        service.getrecFood(basic,price,options).enqueue(object :
            Callback<DietmkResponse> {
            override fun onResponse(call: Call<DietmkResponse>, response: Response<DietmkResponse>) {
                Log.d("test",response.toString())
                Log.d("test",response.body().toString())
                when(response.code()){
                    200 -> {
                        val resp = response.body()!!
                        dietmkView.onDietmkSuccess(resp)
                    }
                    else -> dietmkView.onDietmkFailure("식단 불러오기 중 에러 발생")
                }
            }

            override fun onFailure(call: Call<DietmkResponse>, t: Throwable) {
                dietmkView.onDietmkFailure("네트워크 연결을 확인해 주세요.")
            }
        })
    }
}