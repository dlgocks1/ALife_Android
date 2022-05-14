package com.alife.alife_medifood_android.ui.main

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.ActivityMainBinding
import com.alife.alife_medifood_android.ui.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var mainViewModel: MainViewModel

    override fun initView() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        if(intent.hasExtra("isMakeDiet")){
            mainViewModel.updatedietmk(true)
        }

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.findNavController()
        binding.mainBottomNavi.setupWithNavController(navController)
        binding.mainBottomNavi.itemIconTintList = null
        binding.navHostFragment.isSaveEnabled = false

    }

}