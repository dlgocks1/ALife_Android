package com.alife.alife_medifood_android.data

data class Food(
    val name : String,
    val kcal : String,
    val img : String,
    val price : Int = 0,
    val weight : Int = 0,
    val carbohydrate : Int = 0,
    val protein : Int = 0,
    val fat : Int = 0,
)

data class FoodwithTime(
    val food : Food,
    val time: String,
)

