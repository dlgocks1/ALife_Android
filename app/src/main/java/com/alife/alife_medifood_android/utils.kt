package com.alife.alife_medifood_android

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val gson = Gson()

fun getReposit(): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://18.237.41.72:8080") //
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit
}