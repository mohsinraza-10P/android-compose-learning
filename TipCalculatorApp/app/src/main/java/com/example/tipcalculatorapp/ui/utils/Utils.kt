package com.example.tipcalculatorapp.ui.utils

fun calculateTipAmount(billAmount: Double, tipPercentage: Int): Double {
    val minimumBillAmount = 1
    return if (billAmount > minimumBillAmount) {
        billAmount * tipPercentage / 100
    } else {
        0.0
    }
}

fun calculateTotalPerPerson(billAmount: Double, splitBy: Int, tipPercentage: Int): Double {
    val tip = calculateTipAmount(billAmount, tipPercentage)
    val totalBill = billAmount + tip
    return totalBill / splitBy
}