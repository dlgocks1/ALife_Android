package com.alife.alife_medifood_android.ui.start.signup.service

data class AuthRequest(
    val email: String,
    val password1: String,
    val password2: String
)

data class AuthLoginRequest(
    val email: String,
    val password: String,
)