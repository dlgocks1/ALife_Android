package com.alife.alife_medifood_android.ui.calendar.service

data class UserModifyResponse(
    val allergy: String,
    val avoid_category: String,
    val created_at: String,
    val exercise: String,
    val favor_category: String,
    val gender: String,
    val height: Double,
    val id: Int,
    val updated_at: String,
    val user: Int,
    val vegan_option: String,
    val weight: Double
)