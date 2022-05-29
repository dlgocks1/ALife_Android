package com.alife.alife_medifood_android.ui.shopping

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.FoodMainList
import com.alife.alife_medifood_android.data.FoodSearchList
import com.alife.alife_medifood_android.databinding.FragmentSearchBinding
import com.alife.alife_medifood_android.ui.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private lateinit var shoppingViewModel: ShoppingViewModel

    override fun initViewModel() {
        shoppingViewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding.lifecycleOwner=this
        shoppingViewModel.searchStr.observe(this,{
            binding.shoppingSearchTv.text= it
        })
    }

    override fun initView() {
        val dummydata = listOf(
            FoodSearchList(R.drawable.img_dummy_food1,listOf("저염","무당"),"닭가슴살 부리또",5f,22,12500),
            FoodSearchList(R.drawable.img_dummy_food1,listOf("저염","무당"),"닭가슴살 부리또",5f,22,12500),
            FoodSearchList(R.drawable.img_dummy_food1,listOf("저염","무당"),"닭가슴살 부리또",5f,22,12500),
            FoodSearchList(R.drawable.img_dummy_food1,listOf("저염","무당"),"닭가슴살 부리또",5f,22,12500),
        )

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2)
        binding.shoppingMainrv.layoutManager = mLayoutManager
        val shoppingFoodAdapter = SearchFoodListAdapter()
        shoppingFoodAdapter.setFoods(dummydata)
        binding.shoppingMainrv.adapter = shoppingFoodAdapter

        binding.searchDeleteIv.setOnClickListener {
            shoppingViewModel.setSearchStr("")
        }
    }
}