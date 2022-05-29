package com.alife.alife_medifood_android.ui.shopping

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentShoppingBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.alife.alife_medifood_android.data.FoodMainList
import android.view.inputmethod.EditorInfo
import android.widget.EditText

import android.widget.TextView


class FragmentShopping : BaseFragment<FragmentShoppingBinding>(R.layout.fragment_shopping){
    private lateinit var shoppingViewModel: ShoppingViewModel
    private lateinit var searchText : String

    override fun initViewModel() {
        shoppingViewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding.lifecycleOwner = this

    }
    override fun initView() {
        val dummydata = listOf(
            FoodMainList(R.drawable.img_dummy_food1,"닭가슴살 부리또",5f,22,12500),
            FoodMainList(R.drawable.img_dummy_food1,"닭가슴살 부리또",5f,22,12500),
            FoodMainList(R.drawable.img_dummy_food1,"닭가슴살 부리또",5f,22,12500),
            FoodMainList(R.drawable.img_dummy_food1,"닭가슴살 부리또",5f,22,12500),
        )

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2)
        binding.shoppingMainrv.layoutManager = mLayoutManager
        val shoppingFoodAdapter = ShoppingFoodAdapter()
        shoppingFoodAdapter.setFoods(dummydata)
        binding.shoppingMainrv.adapter = shoppingFoodAdapter

        binding.shoppingEdittv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchText = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.shoppingEdittv.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if(searchText.replace(" ","")!=""){
                    shoppingViewModel.setSearchStr(searchText.trim())
                }
//                val intent = Intent(this,MainActivity::class.java)
//                intent.putExtra("searchStr",searchText.replace(" ",""))
//                startActivity(intent)
                handled = true
            }
            handled
        }

    }
}