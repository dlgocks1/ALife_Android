package com.alife.alife_medifood_android.ui.calendar.service

import com.google.gson.annotations.SerializedName

data class UserDetailRequest(
    @SerializedName("allergy")val allergy: String = "test",
    @SerializedName("avoid_category")val avoid_category: String= "test",
    @SerializedName("exercise")val exercise: String= "test",
    @SerializedName("favor_category")val favor_category: String= "test",
    @SerializedName("gender")val gender: String= "test",
    @SerializedName("height")val height: Double= 125.3,
    @SerializedName("vegan_option")val vegan_option: String= "test",
    @SerializedName("weight")val weight: Double = 128.5,
    @SerializedName("user")val user: Int = 1

)