package com.alife.alife_medifood_android.ui.start.signup.service

import android.util.Log
import com.alife.alife_medifood_android.getReposit
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class AuthService {
    private lateinit var signupView: SignupView
    private lateinit var loginView: LoginView
    private val TAG = "signupService"
    private val gson = Gson()


    fun setloginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun setsignupView(authView: SignupView) {
        this.signupView = authView
    }

    fun signup(signupRequest : AuthRequest) {
        val service = getReposit().create(AuthInterface::class.java)
        service.signup(signupRequest).enqueue(object :
            Callback<signupReponse> {
            override fun onResponse(call: Call<signupReponse>, response: Response<signupReponse>) {
                when(response.code()){
                    201 -> {
                        val resp = response.body()
                        signupView.onSignupSuccess(resp!!.user)
                    }
                    else -> signupView.onSignupFailure("중복된 이메일 입니다.")
                }
            }

            override fun onFailure(call: Call<signupReponse>, t: Throwable) {
                signupView.onSignupFailure("네트워크 연결을 확인해 주세요.")
            }
        })
    }

    fun login(authLoginRequest : AuthLoginRequest){
        val service = getReposit().create(AuthInterface::class.java)
        loginView.onLoginLoading()
        service.login(authLoginRequest).enqueue(object :
            Callback<signupReponse> {
            override fun onResponse(call: Call<signupReponse>, response: Response<signupReponse>) {
                when(response.code()){
                    200 -> {
                        val resp = response.body()
                        loginView.onLoginSuccess(resp!!.user)
                    }
                    else -> loginView.onLoginFailure("회원정보가 없습니다.")
                }
            }
            override fun onFailure(call: Call<signupReponse>, t: Throwable) {
                Log.d(TAG,t.toString())

                loginView.onLoginFailure("네트워크 연결을 확인해 주세요.")
            }
        })
    }

}