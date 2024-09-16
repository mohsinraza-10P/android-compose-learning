package com.example.ktmodule

fun main() {
    val y = arrayOf(1,2,3)
    val z = listOf(1,2,3)

    SpecialFunc().method()

}

class SpecialFunc  {

    fun method() {
        println(MySignal.CLOSED.ordinal)
    }
}

enum class MySignal { OPEN, CLOSED, WORKING }