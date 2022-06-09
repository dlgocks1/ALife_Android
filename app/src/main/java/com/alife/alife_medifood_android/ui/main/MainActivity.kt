package com.alife.alife_medifood_android.ui.main

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.ActivityMainBinding
import com.alife.alife_medifood_android.ui.BaseActivity
import com.alife.alife_medifood_android.utils.KeepStateNavigator

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var mainViewModel: MainViewModel

    override fun initView() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.findNavController()
        // KeepStateNavigator navcontoller에 추가
        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)

        navController.navigatorProvider.addNavigator(navigator)
        // 직접 커스터마이징한 내비게이터를 추가해야 한다면 위와 같이 하드코딩으로 내비게이터를 추가한 후에 setGraph로 그래프를 추가해주어야 내비게이터가 정상적으로 그래프에 반영된다
        navController.setGraph(R.navigation.navigation)
        binding.mainBottomNavi.setupWithNavController(navController)

        binding.mainBottomNavi.itemIconTintList = null
        binding.navHostFragment.isSaveEnabled = false

        // 식단을 추가 했을 때
        if(intent.hasExtra("isMakeDiet")){
            mainViewModel.updatedietmk(true)
            binding.mainBottomNavi.selectedItemId = R.id.fragment_home
        }

    }

}