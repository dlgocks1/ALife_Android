package com.alife.alife_medifood_android.data

data class Food(
    val name : String,
    val kcal : String,
    val img : Int,
    val price : Int = 0,
    val weight : Int = 0,
)
