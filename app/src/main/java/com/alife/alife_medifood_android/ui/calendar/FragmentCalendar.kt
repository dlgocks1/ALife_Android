package com.alife.alife_medifood_android.ui.calendar

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.Food
import com.alife.alife_medifood_android.databinding.FragmentCalendarBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.calendar.service.UserDetailResponse
import com.alife.alife_medifood_android.ui.calendar.service.UserDetailResponseItem
import com.alife.alife_medifood_android.ui.calendar.service.UserDetailService
import com.alife.alife_medifood_android.ui.home.FragmentHomeCalories
import com.alife.alife_medifood_android.ui.home.FragmentHomeEmpty
import com.alife.alife_medifood_android.ui.home.dietmk.DietmkActivity
import com.alife.alife_medifood_android.ui.main.MainViewModel
import com.alife.alife_medifood_android.ui.start.signup.service.UserDetailView
import com.alife.alife_medifood_android.ui.userInfomk.UserinfoAcitivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*
import kotlin.math.roundToInt

class FragmentCalendar : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar),UserDetailView{

    private lateinit var mainViewModel : MainViewModel
    private lateinit var userDetailService : UserDetailService

    override fun initViewModel() {
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        userDetailService = UserDetailService()
        userDetailService.setuserDetailView(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {

        binding.calendarRadiobt1.isChecked = true

        // 월 가져오기
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MMM")
        val formatted = current.format(formatter)
        binding.calendarMonthTv.setText(formatted)

        // 일 가져오기
        val cal = Calendar.getInstance()
        cal.time = Date()
        val df: DateFormat = SimpleDateFormat("yyyy-mm-dd")
        val radiogroup = listOf<RadioButton>(binding.calendarRadiobt1,binding.calendarRadiobt2,binding.calendarRadiobt3,binding.calendarRadiobt4,binding.calendarRadiobt5
        ,binding.calendarRadiobt6,binding.calendarRadiobt7)
        for (i in 0..6) {
            // 날짜를 05.02 형식으로 바꾸어서 week에 더하기
            radiogroup[i].setText("${SimpleDateFormat("EE", Locale.ENGLISH).format(cal.time)} \n${df.format(cal.time).subSequence(8,10)}")
            cal.add(Calendar.DATE, +1)
        }

        // 식단등록이 되어 있으면
        if(mainViewModel.dietmk.value == true){
            binding.calendarDietContainer.visibility= View.VISIBLE
            binding.calendarDietEmptyContainer.visibility= View.GONE

            val calendarAdpater1 = CalendarFoodAdapter()
            val calendarAdpater2 = CalendarFoodAdapter()
            val calendarAdpater3 = CalendarFoodAdapter()
            val dummFoodList1 = mutableListOf<Food>()
            val dummFoodList2 = mutableListOf<Food>()
            val dummFoodList3 = mutableListOf<Food>()
            var morningtotalkcal = 0
            var lunchtotalkcal = 0
            var dinnertotalkcal = 0

            for(i in mainViewModel.foodList.value!!){
                if(i.time == "morning"){
                    dummFoodList1.add(i.food)
                    morningtotalkcal += i.food.kcal.toFloat().roundToInt()
                }else if(i.time =="lunch"){
                    dummFoodList2.add(i.food)
                    lunchtotalkcal += i.food.kcal.toFloat().roundToInt()
                }else{
                    dummFoodList3.add(i.food)
                    dinnertotalkcal += i.food.kcal.toFloat().roundToInt()
                }
            }
            calendarAdpater1.setFoods(dummFoodList1)
            binding.calendarMorningRv.adapter = calendarAdpater1
            if(morningtotalkcal == 0){
                binding.calendarMorningTotalkcalTv.setText("아침이 없습니다.")
            }else{
                binding.calendarMorningTotalkcalTv.setText("Total ${morningtotalkcal}kcal")
            }

            calendarAdpater2.setFoods(dummFoodList2)
            binding.calendarLunchRv.adapter = calendarAdpater2
            if(lunchtotalkcal == 0){
                binding.calendarLunchTotalkcalTv.setText("점심이 없습니다.")
            }else{
                binding.calendarLunchTotalkcalTv.setText("Total ${lunchtotalkcal}kcal")
            }
            
            calendarAdpater3.setFoods(dummFoodList3)
            binding.calendarDinnerRv.adapter = calendarAdpater3
            if(dinnertotalkcal == 0){
                binding.calendarDinnerTotalkcalTv.setText("저녁이 없습니다.")
            }else{
                binding.calendarDinnerTotalkcalTv.setText("Total ${dinnertotalkcal}kcal")
            }
        }else{
            binding.calendarDietEmptyContainer.visibility= View.VISIBLE
            binding.calendarDietContainer.visibility= View.GONE
        }

        
        binding.calendarDietSetting.setOnClickListener {
            userDetailService.getUserDetail()
//            userDetailService.getUserDetail("hc2@naver.com","testtest!")
        }

        binding.calendarDietMakebt.setOnClickListener {
            startActivity(Intent(requireContext(), DietmkActivity::class.java))
        }

    }

    override fun onUserDetailSuccess() {

        val intent = Intent(requireContext(), UserinfoAcitivity::class.java)
        intent.putExtra("isDetail",true)
        startActivity(intent)
    }

    override fun onUserDetailFailure(message: String) {
        val intent = Intent(requireContext(), UserinfoAcitivity::class.java)
        intent.putExtra("isDetail",false)
        startActivity(intent)
    }
    
}