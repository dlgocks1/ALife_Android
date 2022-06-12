package com.alife.alife_medifood_android.ui.home

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentHomeCaloriesBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.main.MainViewModel
import com.hookedonplay.decoviewlib.charts.SeriesItem
import com.hookedonplay.decoviewlib.events.DecoEvent
import kotlin.math.roundToInt


class FragmentHomeCalories : BaseFragment<FragmentHomeCaloriesBinding>(R.layout.fragment_home_calories) {

    private lateinit var mainViewModel : MainViewModel

    override fun initViewModel() {
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun initView() {

        binding.homeContentView.addSeries(SeriesItem.Builder(Color.parseColor("#20000000"))
            .setRange(00f, 100f, 100f)
            .setInitialVisibility(true)
            .setLineWidth(50f)
            .build())

        val seriesItem1 = SeriesItem.Builder(Color.parseColor("#63C6C4"))
            .setRange(0f, 100f, 0f)
            .setLineWidth(50f)
            .build()

        binding.homeContentView.configureAngles(360, -50)
        val series1Index: Int = binding.homeContentView.addSeries(seriesItem1)
        // 이벤트 넣기
//        binding.homeContentView.addEvent(DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
//            .setDelay(1000)
//            .setDuration(2000)
//            .build())

        var totalcalory = 0
        var totalprotein = 0
        var totalcarbo = 0
        var totalfat = 0
        for(i in mainViewModel.foodList.value!!){
            totalcalory += i.food.kcal.toFloat().roundToInt()
            totalprotein += i.food.protein
            totalcarbo += i.food.carbohydrate
            totalfat += i.food.fat
        }

        binding.homeContentProteinTv.setText("${totalprotein/2} / ${totalprotein} g")
        binding.homeContentCarboTv.setText("${totalcarbo/2} / ${totalcarbo} g")
        binding.homeContentFatTv.setText("${totalfat/2} / ${totalfat} g")
        binding.homeContentSodiumTv.setText("${totalfat/2} / ${totalfat} g")
        binding.homeContentSugarTv.setText("${totalfat/2} / ${totalfat} g")
        binding.homeContentCurrentcaloryTv.setText("${totalcalory/2} kcal")
        binding.homeContentTotalcaloryTv.setText("${totalcalory}kcal / daily")
        binding.homeContentView.addEvent(DecoEvent.Builder(50f)
            .setIndex(series1Index)
            .build())
    }
}