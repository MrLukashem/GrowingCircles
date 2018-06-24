package com.mrlukashem.growingcircles.counters


interface ValueCounter<in T> {
    fun calculate(obj: T): Int
}
