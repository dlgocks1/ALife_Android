package com.alife.alife_medifood_android.ui.home.dietmk

import com.alife.alife_medifood_android.R
import com.alife.alife_medifood_android.databinding.FragmentDietmkBudgetBinding
import com.alife.alife_medifood_android.ui.BaseFragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.text.DecimalFormat

class FragmentDietmkBudget : BaseFragment<FragmentDietmkBudgetBinding>(R.layout.fragment_dietmk_budget) {

    private val decimalFormat: DecimalFormat = DecimalFormat("#,###")
    private var result = ""


    private val dietmkViewModel: DietmkViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                DietmkViewModel() as T
        }).get(DietmkViewModel::class.java)
    }

    override fun initView() {
        binding.lifecycleOwner = this
        binding.dietmkViewModel = dietmkViewModel
        binding.dietmkNextBt.setOnClickListener {
            dietmkViewModel.updatedietmk(true)
            dietmkViewModel.updatebudget(binding.dietmkEdittext.text.toString().replace(",",""))
            (activity as DietmkActivity).nextPage()
        }

        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!TextUtils.isEmpty(charSequence.toString()) && charSequence.toString() != result) {
                    result = decimalFormat.format(charSequence.toString().replace(",".toRegex(), "")
                        .toDouble())
                    binding.dietmkEdittext.setText(result)
                    binding.dietmkEdittext.setSelection(result.length)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        }

        binding.dietmk5000bt.setOnClickListener {
            if(binding.dietmkEdittext.text.isBlank()){
                result = decimalFormat.format("5000".replace(",".toRegex(), "")
                    .toDouble())
                binding.dietmkEdittext.setText(result)
                binding.dietmkEdittext.setSelection(result.length)
            }else{
                var number = binding.dietmkEdittext.text.toString().replace(",","").toInt() + 5000
                result = decimalFormat.format(number.toString().replace(",".toRegex(), "")
                    .toDouble())
                binding.dietmkEdittext.setText(result)
                binding.dietmkEdittext.setSelection(result.length)
            }
        }

        binding.dietmk10000bt.setOnClickListener {
            if(binding.dietmkEdittext.text.isBlank()){
                result = decimalFormat.format("10000".replace(",".toRegex(), "")
                    .toDouble())
                binding.dietmkEdittext.setText(result)
                binding.dietmkEdittext.setSelection(result.length)
            }else{
                var number = binding.dietmkEdittext.text.toString().replace(",","").toInt() + 10000
                result = decimalFormat.format(number.toString().replace(",".toRegex(), "")
                    .toDouble())
                binding.dietmkEdittext.setText(result)
                binding.dietmkEdittext.setSelection(result.length)
            }
        }

        binding.dietmk100000bt.setOnClickListener {
            if(binding.dietmkEdittext.text.isBlank()){
                result = decimalFormat.format("100000".replace(",".toRegex(), "")
                    .toDouble())
                binding.dietmkEdittext.setText(result)
                binding.dietmkEdittext.setSelection(result.length)
            }else{
                var number = binding.dietmkEdittext.text.toString().replace(",","").toInt() + 100000
                result = decimalFormat.format(number.toString().replace(",".toRegex(), "")
                    .toDouble())
                binding.dietmkEdittext.setText(result)
                binding.dietmkEdittext.setSelection(result.length)
            }
        }

        binding.dietmk50000bt.setOnClickListener {
            if(binding.dietmkEdittext.text.isBlank()){
                result = decimalFormat.format("50000".replace(",".toRegex(), "")
                    .toDouble())
                binding.dietmkEdittext.setText(result)
                binding.dietmkEdittext.setSelection(result.length)
            }else{
                var number = binding.dietmkEdittext.text.toString().replace(",","").toInt() + 50000
                result = decimalFormat.format(number.toString().replace(",".toRegex(), "")
                    .toDouble())
                binding.dietmkEdittext.setText(result)
                binding.dietmkEdittext.setSelection(result.length)
            }
        }

        binding.dietmkEdittext.addTextChangedListener(watcher)

    }
}