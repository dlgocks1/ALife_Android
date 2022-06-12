package com.alife.alife_medifood_android.ui.shopping

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.data.FoodMainList
import com.alife.alife_medifood_android.data.FoodSearchList
import com.alife.alife_medifood_android.databinding.FragmentSearchBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice.DietmkResponseItem
import com.alife.alife_medifood_android.ui.shopping.service.ShoppingService
import com.alife.alife_medifood_android.ui.start.signup.service.ShoppingView

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search), ShoppingView {
    private lateinit var shoppingViewModel: ShoppingViewModel
    private lateinit var shoppingService : ShoppingService
    private lateinit var searchText : String
    private var isFilter = false

    override fun initViewModel() {
        shoppingViewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding.lifecycleOwner=this
        shoppingViewModel.searchStr.observe(this,{
            binding.shoppingSearchTv.setText(it)
            searchText = it
            val options = mutableMapOf<String,String>()
            options += Pair("product_name",shoppingViewModel.searchStr.value.toString())
            shoppingService.getPList(options)
        })
        shoppingService = ShoppingService()
        shoppingService.setshoppingView(this)
    }

    override fun initView() {
        binding.searchDeleteIv.setOnClickListener {
            shoppingViewModel.setSearchStr("")
        }

        binding.searchFilterIv.setOnClickListener{
            isFilter = !isFilter
            if(isFilter){
                binding.searchFilterContainer.visibility = View.VISIBLE
            }else{
                binding.searchFilterContainer.visibility = View.GONE
            }
        }

        binding.searchFilterAdjustBt.setOnClickListener {
            val options = mutableMapOf<String,String>()
            options += Pair("product_name",searchText)
            if(binding.searchLowPrice.text.isNotBlank()){
                options += Pair("price_low",binding.searchLowPrice.text.toString())
            }
            if(binding.searchHighPrice.text.isNotBlank()){
                options += Pair("price_high",binding.searchHighPrice.text.toString())
            }
            if(binding.searchPriceAsc.isChecked){
                options += Pair("price_order","asc")
            }
            if(binding.searchPriceDesc.isChecked){
                options += Pair("price_order","desc")
            }

            if(binding.searchLowKcal.text.isNotBlank()){
                options += Pair("calory_low",binding.searchLowKcal.text.toString())
            }
            if(binding.searchHighKcal.text.isNotBlank()){
                options += Pair("calory_high",binding.searchHighKcal.text.toString())
            }
            if(binding.searchKcalAsc.isChecked){
                options += Pair("calory_order","asc")
            }
            if(binding.searchKcalDesc.isChecked){
                options += Pair("calory_order","desc")
            }
            shoppingService.getPList(options)
        }

        binding.shoppingSearchTv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchText = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.shoppingSearchTv.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if(searchText.replace(" ","")!=""){
                    shoppingViewModel.setSearchStr(searchText.trim())
                }
                handled = true
            }
            handled
        }
    }

    override fun onPlistLoading() {
        binding.searchProgressBar.visibility = View.VISIBLE
        binding.searchTotalcountTv.text = "검색 중"
        binding.shoppingMainrv.visibility = View.GONE
    }

    override fun onPlistSuccess(foodList: ArrayList<DietmkResponseItem>) {
        val activity = activity
        if(activity != null && foodList.isNotEmpty()){
            binding.searchProgressBar.visibility = View.GONE
            binding.shoppingMainrv.visibility = View.VISIBLE
            binding.searchTotalcountTv.text = "검색 결과 ${ if (foodList.size+1 > 11) 10 else foodList.size}개"
            val dummydata = mutableListOf<FoodSearchList>()
            for(i in 0..kotlin.math.min(foodList.size-1, 11)){
                dummydata.add(FoodSearchList(foodList[i].product_image,listOf(foodList[i].vegan_option,foodList[i].primary_type,foodList[i].cooking_type)
                    ,foodList[i].product_name,5f,0,foodList[i].price))
            }
            val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2)
            binding.shoppingMainrv.layoutManager = mLayoutManager
            val shoppingFoodAdapter = SearchFoodListAdapter()
            shoppingFoodAdapter.setFoods(dummydata)
            binding.shoppingMainrv.adapter = shoppingFoodAdapter

        }else if(activity != null){
            binding.searchProgressBar.visibility = View.GONE
            binding.searchTotalcountTv.text = "검색결과가 없습니다."
        }
    }

    override fun onPlistFailure(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
        binding.shoppingMainrv.visibility = View.VISIBLE
        binding.searchProgressBar.visibility = View.GONE
        binding.searchTotalcountTv.text = "검색결과가 없습니다."
    }
}