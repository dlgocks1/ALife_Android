package com.alife.alife_medifood_android.data

data class FoodSearchList(
    val img : Int,
    val tagList : List<String>,
    val name : String,
    val rating : Float,
    val reviewCount : Int,
    val price : Int
)
