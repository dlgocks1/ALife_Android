package com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice

class DietmkResponse : ArrayList<DietmkResponseItem>()

data class DietmkResponseItem(
    val amount: Double,
    val calory: Double,
    val carbohydrate: Double,
    val cholesterol: Double,
    val company: String,
    val cooking_type: String,
    val fat: Double,
    val id: Int,
    val ingredient: String,
    val price: Int,
    val primary_type: String,
    val product_category: Any,
    val product_image: Any,
    val product_name: String,
    val protein: Double,
    val sat_fat: Double,
    val secondary_type: String,
    val serving_size: Double,
    val sodium: Double,
    val specific: Any,
    val sugar: Double,
    val trans_fat: Double,
    val vegan_option: String
)