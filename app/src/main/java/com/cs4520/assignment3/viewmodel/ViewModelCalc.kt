package com.cs4520.assignment3.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class ViewModelCalc: ViewModel() {
    val num1 = MutableLiveData<Double>()
    val num2 = MutableLiveData<Double>()
    val result = MutableLiveData<String>()

    fun add() {
        doOperation { x, y -> x + y }
    }

    fun multiply() {
        doOperation { x, y -> x * y }
    }

    fun subtract() {
        doOperation { x, y -> x - y }
    }

    fun divide() {
        doOperation { x, y -> if (y != 0.0) x / y else Double.NaN }
    }


    private fun doOperation(operation: (Double, Double) -> Double) {
        try {
            val x = num1.value?.toDouble()
            val y = num2.value?.toDouble()

            if (x == null || y == null) {
                throw NullPointerException()
            }
            result.value = "Result: " +  operation(x, y).toString()


        } catch (e: NumberFormatException) {
            result.value = "Null Result"
        } catch (e: NullPointerException) {
            result.value = "Null Result"
        }
    }
}