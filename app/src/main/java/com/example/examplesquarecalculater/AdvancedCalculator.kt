package com.example.examplesquarecalculater

class AdvancedCalculator: BaseCalculator() {
    override fun calculate(number: Int): Int {
        return number * number
    }

    fun performCalculation(number: Int , operation:(Int)->Int): Int{
        return operation(number)
    }

}