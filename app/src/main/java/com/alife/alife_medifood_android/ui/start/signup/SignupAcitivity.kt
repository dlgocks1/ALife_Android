package com.alife.alife_medifood_android.ui.start.signup

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
import com.alife.alife_medifood_android.databinding.ActivitySignupBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.ui.main.MainActivity
import com.alife.alife_medifood_android.ui.start.signup.service.*
import com.alife.alife_medifood_android.ui.userInfomk.FragmentSignupBody
import com.alife.alife_medifood_android.ui.userInfomk.FragmentSignupFoodcategory
import com.alife.alife_medifood_android.ui.userInfomk.FragmentSignupFoodmanagement

class SignupAcitivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup), SignupView {

    private lateinit var signupViewModel: SignupViewModel
    private var backKeyPressedTime: Long = 0
    private lateinit var signupService : AuthService
    val buttonListener = View.OnClickListener {
        when (it.id) {
            R.id.signup_next_bt -> nextPage()
            R.id.signup_back_iv -> undoPage()
        }
    }

    override fun initView() {
        signupViewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        val signupViewPagerAdapter = PagerAdapter(this)
        binding.lifecycleOwner = this
        binding.signupViewModel = signupViewModel
        signupViewModel.setAdapter(signupViewPagerAdapter)
        binding.signupVp.adapter = signupViewModel.adapter.value
    }

    override fun initViewModel() {
        signupViewModel.email.observe(this, Observer {
            Log.d(TAG,it)
        })
        signupService = AuthService()
        signupService.setsignupView(this)
    }

    override fun initListener() {
        binding.signupVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                signupViewModel.setCurrentPage(position)
            }
        })
    }

    fun undoPage() {
        if (binding.signupVp.currentItem != 0) {
            binding.signupVp.currentItem = binding.signupVp.currentItem.minus(1)
        }else{
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis()
                Toast.makeText(this, "회원가입이 끝나지 않았어요. 종료하시겠어요?", Toast.LENGTH_SHORT).show()
            } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                super.onBackPressed()
//                finish()
            }
        }
    }

    fun nextPage() {
        if (binding.signupVp.currentItem != 0) {
            binding.signupVp.currentItem = binding.signupVp.currentItem.plus(1)
        }else{
            signupService.signup(signupRequest = AuthRequest(signupViewModel.email.value.toString(),
                signupViewModel.password.value.toString(),signupViewModel.password.value.toString()))
        }
    }

    class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 1
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FragmentSignupEmail()
                1 -> FragmentSignupBody()
                2 -> FragmentSignupFoodcategory()
                3 -> FragmentSignupFoodmanagement()
                else -> FragmentSignupFoodcategory()
            }
        }
    }

    override fun onBackPressed() {
        if (binding.signupVp.currentItem == 0) {
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
        private const val TAG = "SignupAcitivity"
    }

    override fun onSignupSuccess(user : User) {
        AlifeApplication.useremail = signupViewModel.email.value.toString()
        AlifeApplication.password = signupViewModel.password.value.toString()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("Email",signupViewModel.email.value.toString())
        intent.putExtra("Password",signupViewModel.password.value.toString())
        startActivity(intent)
    }

    override fun onSignupFailure(message : String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}