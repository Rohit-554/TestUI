package com.example.test

data class SpendClass(
    val image:Int,
    val ItemName:String,
    val Date:String,
    val spendCost:String,
    var isSelected:Boolean? = false
)