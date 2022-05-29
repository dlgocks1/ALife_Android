package com.alife.alife_medifood_android.ui.home

import android.graphics.Color
import android.graphics.drawable.Drawable
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentHomeCaloriesBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.hookedonplay.decoviewlib.charts.SeriesItem
import com.hookedonplay.decoviewlib.events.DecoEvent


class FragmentHomeCalories : BaseFragment<FragmentHomeCaloriesBinding>(R.layout.fragment_home_calories) {
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
        binding.homeContentView.addEvent(DecoEvent.Builder(58f)
            .setIndex(series1Index)
            .build())
    }
}