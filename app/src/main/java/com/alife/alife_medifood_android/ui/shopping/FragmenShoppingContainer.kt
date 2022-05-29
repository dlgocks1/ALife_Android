package com.alife.alife_medifood_android.ui.shopping

import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentShoppingContainerBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.home.FragmentHomeCalories
import com.alife.alife_medifood_android.ui.home.FragmentHomeEmpty

class FragmenShoppingContainer : BaseFragment<FragmentShoppingContainerBinding>(R.layout.fragment_shopping_container) {

    private lateinit var shoppingViewModel: ShoppingViewModel

    override fun initViewModel() {
        shoppingViewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding.lifecycleOwner=this
        shoppingViewModel.searchStr.observe(this,{
            if(it.trim() == ""){
                fragmentManager!!.beginTransaction().replace(R.id.fragment_shopping_container, FragmentShopping()).commit()
            }else{
                fragmentManager!!.beginTransaction().replace(R.id.fragment_shopping_container, SearchFragment()).commit()
            }
        })
    }

    override fun initView() {

    }

}