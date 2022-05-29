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
        binding.dietmkEdittext.addTextChangedListener(watcher)

    }
}