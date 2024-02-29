package com.cs4520.assignment3.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cs4520.assignment3.viewmodel.ViewModelCalc
import com.cs4520.assignment3.databinding.FragmentMvvmBinding

class MvvmFragment : Fragment() {
    private val viewModel: ViewModelCalc by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMvvmBinding.inflate(inflater, container, false)

        binding.addButton.setOnClickListener { viewModel.add() }

        binding.multiplyButton.setOnClickListener { viewModel.multiply() }

        binding.subtractButton.setOnClickListener { viewModel.subtract() }

        binding.divideButton.setOnClickListener { viewModel.divide() }

        binding.num1Field.addTextChangedListener(MyTextWatcher(viewModel.num1))

        binding.num2Field.addTextChangedListener(MyTextWatcher(viewModel.num2))

        viewModel.result.observe(viewLifecycleOwner) {
            if (it == null ) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                binding.resultField.text = ""
            }
            binding.resultField.text = it
        }
        return binding.root
    }
}

private class MyTextWatcher(private val data: MutableLiveData<Double>) : TextWatcher {
    private var lastValue: Double? = null
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        lastValue = data.value
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(s: Editable?) {
        val newValue = s.toString().toDoubleOrNull()
        if (lastValue == newValue) return
        data.value = s.toString().toDoubleOrNull()
    }
}