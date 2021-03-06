package com.alife.alife_medifood_android.ui.home.dietmk

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.ActivityDietmkBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.ui.main.MainActivity
import com.alife.alife_medifood_android.ui.userInfomk.FragmentSignupFoodfavor

class DietmkActivity : BaseActivity<ActivityDietmkBinding>(R.layout.activity_dietmk) {

    private lateinit var dietmkViewModel: DietmkViewModel
    private var backKeyPressedTime: Long = 0
    private val TOTAL_DIETMAKE_PAGE_NUM = 3

    override fun initView() {
        binding.lifecycleOwner = this
        dietmkViewModel = ViewModelProvider(this).get(DietmkViewModel::class.java)

        val dietViewpagerAdpater=  DietmkPagerAdapter(this)
        binding.dietdayContainer.adapter = dietViewpagerAdpater
        binding.dietmkViewModel = dietmkViewModel

        dietmkViewModel.foodList.observe(this,{
            Log.d("test",it.toString())
        })
    }

    class DietmkPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FragmentSignupFoodfavor("Dietmk")
                1 -> FragmentDietmkBudget()
                2 -> FragmentDietmkSelectFood()
                else -> FragmentSignupFoodfavor("Dietmk")
            }
        }
    }

    val buttonListener = View.OnClickListener {
        when (it.id) {
            R.id.dietmk_next_bt -> nextPage()
//            R.id.dietmk_back_iv -> undoPage()
        }
    }

    fun undoPage() {
        if (binding.dietdayContainer.currentItem != 0) {
            binding.dietdayContainer.currentItem = binding.dietdayContainer.currentItem.minus(1)
        }else{
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis()
                Toast.makeText(this, "??????????????? ????????? ????????????. ??????????????????????", Toast.LENGTH_SHORT).show()
            } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                super.onBackPressed()
            }
        }
    }

    fun nextPage() {
        if (binding.dietdayContainer.currentItem != TOTAL_DIETMAKE_PAGE_NUM) {
            binding.dietdayContainer.currentItem = binding.dietdayContainer.currentItem.plus(1)
        }else{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("isMakeDiet",dietmkViewModel.dietmk.value)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (binding.dietdayContainer.currentItem == 0) {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis()
                Toast.makeText(this, "??????????????? ????????? ????????????. ??????????????????????", Toast.LENGTH_SHORT).show()
            } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                super.onBackPressed()
//                finish()
            }
        } else {
            undoPage()
        }
    }
}