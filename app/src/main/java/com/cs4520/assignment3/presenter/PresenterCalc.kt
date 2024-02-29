package com.cs4520.assignment3.presenter

import com.cs4520.assignment3.view.ViewCalc

class PresenterCalc(private val view: ViewCalc) {

    // make the operations
    fun subtract(num1: Double?, num2: Double?) {
        doOperation(num1, num2){
        x, y -> x - y }
    }

    fun add(num1: Double?, num2: Double?) {
        doOperation(num1, num2){
        x, y -> x + y }
    }

    fun divide(num1: Double?, num2: Double?) {
        doOperation(num1, num2){
        x, y -> if (y != 0.0) x / y else Double.NaN }
    }

    fun multiply(num1: Double?, num2: Double?) {
        doOperation(num1, num2){
        x, y -> y * x }
    }

    // make the helper function for the operations
    private fun doOperation(x: Double?, y: Double?, operation: (Double, Double) -> Double){
        try {
            // check for any null pointers
            if (x == null || y == null) {
                throw NullPointerException()
            } else {
                val solution = operation(x, y)
                view.displayResult("Result: " + solution.toString())
            }
            //catch the null error and prevent the app from closing
            //intstead it'll present a toast "null" message
        } catch (e: NumberFormatException) {
            view.displayError("Null Result")
            view.displayResult("")
        } catch (e: NullPointerException) {
            view.displayError("Null Result")
            view.displayResult("")
        }
    }
}