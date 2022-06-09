package com.alife.alife_medifood_android.ui.calendar.service

import android.util.Log
import com.alife.AlifeApplication
import com.alife.alife_medifood_android.ui.start.signup.service.*
import com.google.gson.Gson
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import okhttp3.Interceptor
import java.io.IOException


class UserDetailService {
    private lateinit var userDetailView: UserDetailView
    private val TAG = "UserDetailView"
    private val gson = Gson()

    fun getReposit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://218.50.154.150:8080") //
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    fun setuserDetailView(userDetailView: UserDetailView) {
        this.userDetailView = userDetailView
    }

    fun getUserDetail() {
        val service = getReposit().create(UserDetailInterface::class.java)
        val basic = Credentials.basic("hc2@naver.com", "testtest!")
//        val basic = Credentials.basic(AlifeApplication.useremail, AlifeApplication.password)
        service.getuserDetail(basic).enqueue(object :
            Callback<UserDetailResponse> {
            override fun onResponse(call: Call<UserDetailResponse>, response: Response<UserDetailResponse>) {
                when(response.code()){
                    200 -> {
                        val resp = response.body()
                        userDetailView.onUserDetailSuccess()
                    }
                    else -> userDetailView.onUserDetailFailure("등록되어있지 않습니다.")
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                userDetailView.onUserDetailFailure("네트워크 연결을 확인해 주세요.")
            }
        })
    }

    fun postUserDetail(userDetailRequest: UserDetailRequest) {
        val service = getReposit().create(UserDetailInterface::class.java)
        val basic = Credentials.basic("hc2@naver.com", "testtest!")
        Log.d("test","post"+userDetailRequest.toString())
        service.postuserDetail(basic,userDetailRequest).enqueue(object :
            Callback<UserModifyResponse> {
            override fun onResponse(call: Call<UserModifyResponse>, response: Response<UserModifyResponse>) {
                when(response.code()){
                    200 -> {
//                        val resp = response.body()
                        userDetailView.onUserDetailSuccess()
                    }
                    else -> userDetailView.onUserDetailFailure("네트워크 연결을 확인해 주세요.")
                }
            }

            override fun onFailure(call: Call<UserModifyResponse>, t: Throwable) {
                userDetailView.onUserDetailFailure("네트워크 연결을 확인해 주세요.")
            }
        })
    }

    fun modifyUserDetail(userDetailRequest: UserDetailRequest) {
        val service = getReposit().create(UserDetailInterface::class.java)
        val basic = Credentials.basic("hc2@naver.com", "testtest!")
        Log.d("test","modify"+userDetailRequest.toString())
        service.modifyuserDetail(basic,userDetailRequest).enqueue(object :
            Callback<UserModifyResponse> {
            override fun onResponse(call: Call<UserModifyResponse>, response: Response<UserModifyResponse>) {
                Log.d("test",response.toString())
                when(response.code()){
                    200 -> {
                        val resp = response.body()
                        userDetailView.onUserDetailSuccess()
                    }
                    else -> userDetailView.onUserDetailFailure("네트워크 연결을 확인해 주세요.")
                }
            }
            override fun onFailure(call: Call<UserModifyResponse>, t: Throwable) {
                userDetailView.onUserDetailFailure("네트워크 연결을 확인해 주세요.")
            }
        })
    }

}
