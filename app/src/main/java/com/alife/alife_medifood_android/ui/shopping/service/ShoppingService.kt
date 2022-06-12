package com.alife.alife_medifood_android.ui.shopping.service

import android.util.Log
import com.alife.alife_medifood_android.getReposit
import com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice.DietmkInterface
import com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice.DietmkResponse
import com.alife.alife_medifood_android.ui.start.signup.service.DietmkView
import com.alife.alife_medifood_android.ui.start.signup.service.ShoppingView
import com.google.gson.Gson
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingService {
    private lateinit var shoppingView: ShoppingView

    fun setshoppingView(shoppingView: ShoppingView) {
        this.shoppingView = shoppingView
    }

    fun getPList(options : Map<String, String>) {
        val service = getReposit().create(ShoppingInterface::class.java)
        val basic = Credentials.basic("hc2@naver.com", "testtest!")
        shoppingView.onPlistLoading()
        service.getPlist(basic,options).enqueue(object :
            Callback<DietmkResponse> {
            override fun onResponse(call: Call<DietmkResponse>, response: Response<DietmkResponse>) {
                Log.d("test",response.toString())
                Log.d("test",response.body().toString())
                when(response.code()){
                    200 -> {
                        val resp = response.body()!!
                        shoppingView.onPlistSuccess(resp)
                    }
                    else -> shoppingView.onPlistFailure("상품 불러오기 중 에러 발생")
                }
            }

            override fun onFailure(call: Call<DietmkResponse>, t: Throwable) {
                shoppingView.onPlistFailure("네트워크 연결을 확인해 주세요.")
            }
        })
    }
}