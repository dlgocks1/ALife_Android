package com.alife.alife_medifood_android.ui.userInfomk

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alife.AlifeApplication
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.ActivityUserinfoBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.ui.calendar.service.UserDetailRequest
import com.alife.alife_medifood_android.ui.calendar.service.UserDetailResponseItem
import com.alife.alife_medifood_android.ui.calendar.service.UserDetailService
import com.alife.alife_medifood_android.ui.main.MainActivity
import com.alife.alife_medifood_android.ui.start.signup.service.AuthService
import com.alife.alife_medifood_android.ui.start.signup.service.UserDetailView

class UserinfoAcitivity : BaseActivity<ActivityUserinfoBinding>(R.layout.activity_userinfo),UserDetailView{

    private lateinit var userinfoViewModel: UserinfoViewModel
    private var backKeyPressedTime: Long = 0

    private lateinit var userdetailService: UserDetailService
    var isDetail = false

    val buttonListener = View.OnClickListener {
        when (it.id) {
            R.id.signup_next_bt -> nextPage()
            R.id.signup_back_iv -> undoPage()
        }
    }

    override fun initViewModel() {
        if(intent.hasExtra("isDetail")){
            isDetail = intent.getBooleanExtra("isDetail",false)
        }
        // 로그인
        userdetailService = UserDetailService()
        userdetailService.setuserDetailView(this)
    }

    override fun initView() {
        userinfoViewModel = ViewModelProvider(this).get(UserinfoViewModel::class.java)
        val userinfoViewPagerAdapter = PagerAdapter(this)
        binding.lifecycleOwner = this
        binding.userinfoVp.adapter = userinfoViewPagerAdapter
    }

    override fun initListener() {
        binding.userinfoVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                userinfoViewModel.setCurrentPage(position)
            }
        })
    }

    fun undoPage() {
        if (binding.userinfoVp.currentItem != 0) {
            binding.userinfoVp.currentItem = binding.userinfoVp.currentItem.minus(1)
        }else{
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis()
                Toast.makeText(this, "회원정보 작성이 끝나지 않았어요. \n 종료하시겠어요?", Toast.LENGTH_SHORT).show()
            } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                super.onBackPressed()
//                finish()
            }
        }
    }

    fun nextPage() {
        if (binding.userinfoVp.currentItem <= 4) {
            binding.userinfoVp.currentItem = binding.userinfoVp.currentItem.plus(1)
        }else{
            if(isDetail){
                // 식단 등록 되어 있을 때
                userdetailService.modifyUserDetail(userDetailRequest = UserDetailRequest(
                    userinfoViewModel.allergy.value!!,
                    "AvoidCategory",
                    userinfoViewModel.exercise.value!!,
                    "FavorCategory",
                    userinfoViewModel.gender.value!!,
                    userinfoViewModel.height.value!!.toDouble(),
                    "Veganoption",
                    userinfoViewModel.weight.value!!.toDouble()
                ))
            }else{
                // 안되어 있을 때
                userdetailService.postUserDetail(userDetailRequest = UserDetailRequest(
                    userinfoViewModel.allergy.value!!,
                    "AvoidCategory",
                    userinfoViewModel.exercise.value!!,
                    "FavorCategory",
                    userinfoViewModel.gender.value!!,
                    userinfoViewModel.height.value!!.toDouble(),
                    "Veganoption",
                    userinfoViewModel.weight.value!!.toDouble()
                ))
            }
        }
    }

    class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 6
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FragmentSignupBody()
                1 -> FragmentSignupExercise ()
                2 -> FragmentSignupFoodcategory()
                3 -> FragmentSignupFoodAllergy()
                4 -> FragmentSignupFoodmanagement()
                5 -> FragmentSignupFoodfavor("Signup")
                else -> FragmentSignupFoodcategory()
            }
        }
    }

    override fun onBackPressed() {
        if (binding.userinfoVp.currentItem == 0) {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis()
                Toast.makeText(this, "회원가입이 끝나지 않았어요. 종료하시겠어요?", Toast.LENGTH_SHORT).show()
            } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                super.onBackPressed()
//                finish()
            }
        } else {
            undoPage()
        }
    }

    companion object {
        private const val TAG = "UserinfAcitivity"
    }

    override fun onUserDetailSuccess() {
        Toast.makeText(this,"유저 정보를 반영했습니다.",Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onUserDetailFailure(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

}