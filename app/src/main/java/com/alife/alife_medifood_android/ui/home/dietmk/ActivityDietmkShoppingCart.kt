package com.alife.alife_medifood_android.ui.home.dietmk

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.Food
import com.alife.alife_medifood_android.data.FoodwithTime
import com.alife.alife_medifood_android.databinding.ActivityDietmkShoppingCartBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.main.MainActivity
import com.google.gson.Gson
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class ActivityDietmkShoppingCart : BaseActivity<ActivityDietmkShoppingCartBinding>(R.layout.activity_dietmk_shopping_cart) {

    private var totalprice = 0
    private var totalkcal = 0
    private var totalcount = 0
    private val foodListforResult = mutableListOf<FoodwithTime>()

    override fun initViewModel() {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {

        val dietmkFoodListAdapter = DietmkShoppingCartFoodListAdapter()

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
        binding.dietmkShoppingCartDayTv.setText(current.format(formatter))


        if(intent.hasExtra("FoodList")){
            val gson = Gson()
            val foodList = gson.fromJson(intent.getStringExtra("FoodList"), Array<Food>::class.java).toList()
            dietmkFoodListAdapter.setFoods(foodList)

            dietmkFoodListAdapter.setOnItemClickListener(object : DietmkShoppingCartFoodListAdapter.OnClickInterface{
                override fun onItemClick(food: Food,time:String, check: Boolean) {
                    // 체크되어있으며, 중복으로 들어가 있다면
                    if(check &&
                        (foodListforResult.contains(FoodwithTime(food,"morning")) ||
                        foodListforResult.contains(FoodwithTime(food,"lunch"))  ||
                        foodListforResult.contains(FoodwithTime(food,"dinner"))  )){
                        foodListforResult.remove(FoodwithTime(food,"morning"))
                        foodListforResult.remove(FoodwithTime(food,"lunch"))
                        foodListforResult.remove(FoodwithTime(food,"dinner"))
                        foodListforResult.add(FoodwithTime(food,time))
                    }else if(check){
                        totalprice += food.price
                        totalkcal += food.kcal.toFloat().roundToInt()
                        totalcount += 1
                        foodListforResult.add(FoodwithTime(food,time))
                    }else if(!check &&
                        (foodListforResult.contains(FoodwithTime(food,"morning"))  ||
                        foodListforResult.contains(FoodwithTime(food,"lunch")) ||
                        foodListforResult.contains(FoodwithTime(food,"dinner")) )){
                        totalcount -= 1
                        totalprice -= food.price
                        totalkcal -= food.kcal.toFloat().roundToInt()
                        foodListforResult.remove(FoodwithTime(food,time))
                    }
                    val decimalFormat: DecimalFormat = DecimalFormat("#,###")
                    binding.dietmkShoppingCartTotalpriceTv.text = "${decimalFormat.format(totalprice.toString().replace(",".toRegex(), "").toDouble())} 원"
                    binding.dietmkShoppingCartTotalkcalTv.text = "${decimalFormat.format(totalkcal.toString().replace(",".toRegex(), "").toDouble())} kcal"
                    binding.dietmkShoppingCartTotalcountTv.text="선택 상품 ${totalcount}개"
                }
            })
            binding.dietmkShoppingCartRv.adapter = dietmkFoodListAdapter
        }

        binding.dietmkShoppingCartPurchaseBt.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("isMakeDiet",true)
            intent.putExtra("FoodList",Gson().toJson(foodListforResult))
            startActivity(intent)
        }
    }
}