package com.alife.alife_medifood_android.ui.start.signup.service

data class signupReponse(
    val access_token: String,
    val refresh_token: String,
    val user: User
)

data class User(
    val email: String,
    val first_name: String,
    val last_name: String,
    val pk: Int
)