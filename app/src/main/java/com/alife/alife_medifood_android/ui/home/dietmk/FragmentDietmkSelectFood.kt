package com.alife.alife_medifood_android.ui.home.dietmk

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.Food
import com.alife.alife_medifood_android.databinding.ActivityShoppingcartBinding
import com.alife.alife_medifood_android.databinding.FragmentDietmkSelectFoodBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice.DietmkResponseItem
import com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice.DietmkService
import com.alife.alife_medifood_android.ui.start.signup.service.DietmkView
import com.google.gson.Gson
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class FragmentDietmkSelectFood : BaseFragment<FragmentDietmkSelectFoodBinding>(R.layout.fragment_dietmk_select_food),DietmkView {

    private lateinit var dietmkService : DietmkService
    private lateinit var dietmkViewModel: DietmkViewModel
    private var moreinfoflag = false

    override fun initViewModel() {
        dietmkService = DietmkService()
        dietmkService.setdietmkView(this)
        dietmkViewModel = ViewModelProvider(requireActivity()).get(DietmkViewModel::class.java)
        binding.lifecycleOwner = this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        dietmkService.getRecFood(dietmkViewModel.budget.value!!.toInt() * 5)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
        binding.dietmkDayTv.setText(current.format(formatter))

        binding.dietmkNextLa.setOnClickListener {
//            (activity as DietmkActivity).nextPage()
            val gson = Gson()
            val intent = Intent(requireActivity(),ActivityDietmkShoppingCart::class.java)
            intent.putExtra("FoodList",gson.toJson(dietmkViewModel.foodList.value))
            startActivity(intent)
        }

        binding.dietmkPriceProgressbar.max = dietmkViewModel.budget.value!!.toInt()
        binding.dietmkKcalProgressbar.max = 2500
        binding.dietmkProteinProgressbar.max = 150
        binding.dietmkCarbohydrateProgressbar.max = 350
        binding.dietmkFatProgressbar.max = 100

        binding.dietmkFoodprogressbarTv.setText("0/${dietmkViewModel.budget.value!!}")
        binding.dietmkKcalprogressbarTv.setText("0/2500")
        binding.dietmkProteinprogressbarTv.setText("0/150")
        binding.dietmkCarbohydrateprogressbarTv.setText("0/350")
        binding.dietmkFatprogressbarTv.setText("0/100")

        binding.dietmkKcalProgressbar.progress = 0
        binding.dietmkPriceProgressbar.progress = 0
        binding.dietmkProteinProgressbar.progress = 0
        binding.dietmkCarbohydrateProgressbar.progress = 0
        binding.dietmkFatProgressbar.progress = 0

        binding.dietmkMoreinfoIv.setOnClickListener {
            if(!moreinfoflag){
                binding.dietmkMorinfoLayout.visibility=View.VISIBLE
                binding.dietmkMoreinfoIv.rotation = 180f
            }else{
                binding.dietmkMorinfoLayout.visibility=View.GONE
                binding.dietmkMoreinfoIv.rotation = 0f
            }
            moreinfoflag = !moreinfoflag
        }
    }

    override fun onDeitmkLoading() {
        binding.dietmkLoadingIv.visibility=View.VISIBLE
        binding.dietmkBackgroundLayout.visibility=View.GONE
    }

    override fun onDietmkSuccess(foodList: ArrayList<DietmkResponseItem>) {
        binding.dietmkLoadingIv.visibility=View.GONE
        binding.dietmkBackgroundLayout.visibility=View.VISIBLE
        var foodListforAdapter = mutableListOf<Food>()
        for(i in 0 until Math.min(foodList.size,7)){
            foodListforAdapter += Food(foodList[i].product_name,foodList[i].calory.toString(),R.drawable.img_dummy_food,foodList[i].price,foodList[i].amount.roundToInt(),foodList[i].carbohydrate.roundToInt()
            ,foodList[i].protein.roundToInt(),foodList[i].fat.roundToInt())
        }
        val dietmkFoodListAdapter = DietmkFoodListAdapter()

        dietmkFoodListAdapter.setOnItemClickListener(object : DietmkFoodListAdapter.OnClickInterface{
            override fun onItemClick(food:Food,ischecked: Boolean) {
                if(ischecked){
                    dietmkViewModel.updatenowbudget(dietmkViewModel.nowbudget.value!! + food.price)
                    dietmkViewModel.updatenowkcal(dietmkViewModel.nowkcal.value!! + food.kcal.toFloat().roundToInt())
                    dietmkViewModel.updatenowfat(dietmkViewModel.nowfat.value!! + food.fat)
                    dietmkViewModel.updatenowprotein(dietmkViewModel.nowprotein.value!! + food.protein)
                    dietmkViewModel.updatenowcarbo(dietmkViewModel.nowcarbo.value!! + food.carbohydrate)
                    dietmkViewModel.addFoodList(food)
                }else{
                    dietmkViewModel.updatenowbudget(dietmkViewModel.nowbudget.value!! - food.price)
                    dietmkViewModel.updatenowkcal(dietmkViewModel.nowkcal.value!! - food.kcal.toFloat().roundToInt())
                    dietmkViewModel.updatenowfat(dietmkViewModel.nowfat.value!! - food.fat)
                    dietmkViewModel.updatenowprotein(dietmkViewModel.nowprotein.value!! - food.protein)
                    dietmkViewModel.updatenowcarbo(dietmkViewModel.nowcarbo.value!! - food.carbohydrate)
                    dietmkViewModel.removeFoodList(food)
                }
//                Log.d("test",dietmkViewModel.foodList.value.toString())
                binding.dietmkPriceProgressbar.setProgress(dietmkViewModel.nowbudget.value!!,true)
                binding.dietmkKcalProgressbar.setProgress(dietmkViewModel.nowkcal.value!!,true)
                binding.dietmkCarbohydrateProgressbar.setProgress(dietmkViewModel.nowcarbo.value!!,true)
                binding.dietmkProteinProgressbar.setProgress(dietmkViewModel.nowprotein.value!!,true)
                binding.dietmkFatProgressbar.setProgress(dietmkViewModel.nowfat.value!!,true)

                binding.dietmkFoodprogressbarTv.setText("${dietmkViewModel.nowbudget.value!!}/${dietmkViewModel.budget.value.toString()}")
                binding.dietmkKcalprogressbarTv.setText("${dietmkViewModel.nowkcal.value!!}/2500")
                binding.dietmkCarbohydrateprogressbarTv.setText("${dietmkViewModel.nowcarbo.value!!}/350")
                binding.dietmkProteinprogressbarTv.setText("${dietmkViewModel.nowprotein.value!!}/150")
                binding.dietmkFatprogressbarTv.setText("${dietmkViewModel.nowfat.value!!}/100")

                if(binding.dietmkPriceProgressbar.max <= binding.dietmkPriceProgressbar.progress){
                    binding.dietmkFoodprogressbarTv.setTextColor(Color.parseColor("#FF0000"))
                }else{
                    binding.dietmkFoodprogressbarTv.setTextColor(Color.parseColor("#888888"))
                }

                if(binding.dietmkKcalProgressbar.max <= binding.dietmkKcalProgressbar.progress){
                    binding.dietmkKcalprogressbarTv.setTextColor(Color.parseColor("#FF0000"))
                }else{
                    binding.dietmkKcalprogressbarTv.setTextColor(Color.parseColor("#888888"))
                }

                if(binding.dietmkFatProgressbar.max <= binding.dietmkFatProgressbar.progress){
                    binding.dietmkFatprogressbarTv.setTextColor(Color.parseColor("#FF0000"))
                }else{
                    binding.dietmkFatprogressbarTv.setTextColor(Color.parseColor("#888888"))
                }

                if(binding.dietmkProteinProgressbar.max <= binding.dietmkProteinProgressbar.progress){
                    binding.dietmkProteinprogressbarTv.setTextColor(Color.parseColor("#FF0000"))
                }else{
                    binding.dietmkProteinprogressbarTv.setTextColor(Color.parseColor("#888888"))
                }

                if(binding.dietmkCarbohydrateProgressbar.max <= binding.dietmkCarbohydrateProgressbar.progress){
                    binding.dietmkCarbohydrateprogressbarTv.setTextColor(Color.parseColor("#FF0000"))
                }else{
                    binding.dietmkCarbohydrateprogressbarTv.setTextColor(Color.parseColor("#888888"))
                }

                val decimalFormat: DecimalFormat = DecimalFormat("#,###")
                val price = decimalFormat.format(dietmkViewModel.nowbudget.value!!.toString().replace(",".toRegex(), "").toDouble())
                binding.dietmkTotalTv.setText("Total ${price} 원")
            }
        })

        dietmkFoodListAdapter.setFoods(foodListforAdapter)
        binding.dietmkFoodRv.adapter = dietmkFoodListAdapter
        binding.dietmkFoodRv2.adapter = dietmkFoodListAdapter

    }

    override fun onDietmkFailure(message: String) {
        binding.dietmkLoadingIv.visibility=View.GONE
        binding.dietmkBackgroundLayout.visibility=View.VISIBLE
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }



}