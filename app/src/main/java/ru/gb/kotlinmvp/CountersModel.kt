package ru.gb.kotlinmvp

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    fun getCurrent(position: Int): Int {
        return counters[position]
    }

    fun next(position: Int): Int {
        return counters[position]++
    }

    fun set(position: Int, value: Int) {
        counters[position] = value
    }
}
