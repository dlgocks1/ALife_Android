package com.alife.alife_medifood_android.ui.main.calendar

import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.Food
import com.alife.alife_medifood_android.databinding.FragmentCalendarBinding
import com.alife.alife_medifood_android.ui.BaseFragment

class FragmentCalendar : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar){

    override fun initView() {
//        binding.lifecycleOwner= this
        val moringadpater = CalendarFoodAdapter()
        val dummyfoodList = arrayOf(
            Food("순두부 국","253kcal",R.drawable.img_dummy_food),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food),
            Food("순두부 국","253kcal",R.drawable.img_dummy_food))
        moringadpater.setFoods(dummyfoodList.toList())
        binding.calendarMorningRv.adapter = moringadpater
        binding.calendarLunchRv.adapter = moringadpater

        binding.calendarRadiobt1.isChecked = true
    }
}