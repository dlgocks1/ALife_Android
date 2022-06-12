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
import android.widget.Toast
import com.alife.alife_medifood_android.ui.home.dietmk.dietmkservice.DietmkResponseItem
import com.alife.alife_medifood_android.ui.shopping.service.ShoppingService
import com.alife.alife_medifood_android.ui.start.signup.service.ShoppingView
import java.lang.Integer.min


class FragmentShopping : BaseFragment<FragmentShoppingBinding>(R.layout.fragment_shopping),ShoppingView{
    private lateinit var shoppingViewModel: ShoppingViewModel
    private lateinit var searchText : String
    private lateinit var shoppingService : ShoppingService

    override fun initViewModel() {
        shoppingViewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding.lifecycleOwner = this
        shoppingService = ShoppingService()
        shoppingService.setshoppingView(this)
    }
    override fun initView() {
        val options = mutableMapOf<String,String>()
        shoppingService.getPList(options)
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

    override fun onPlistLoading() {
        binding.shoppingPlistProgressbar.visibility = View.VISIBLE
    }

    override fun onPlistSuccess(foodList: ArrayList<DietmkResponseItem>) {
        val activity = activity
        if(activity != null){
            binding.shoppingPlistProgressbar.visibility = View.GONE
            val dummydata = mutableListOf<FoodMainList>()
            for(i in 0..kotlin.math.min(foodList.size-1, 7)){
                dummydata.add(FoodMainList(foodList[i].product_image, foodList[i].product_name,5f,0,foodList[i].price))
            }
            val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2)
            binding.shoppingMainrv.layoutManager = mLayoutManager
            val shoppingFoodAdapter = ShoppingFoodAdapter()
            shoppingFoodAdapter.setFoods(dummydata)
            binding.shoppingMainrv.adapter = shoppingFoodAdapter
        }
    }

    override fun onPlistFailure(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
        binding.shoppingPlistProgressbar.visibility = View.GONE
    }
}