package com.mrlukashem.mediacontentprovider.multithreading

/**
 * Created by MrLukashem on 20.03.2018.
 */
typealias Task<T> = () -> T
typealias Callback<T> = (T) -> Unit

interface Dispatcher<T> {
    fun dispatch(task: Task<T>, cb: Callback<T>)
    fun begin()
    fun quit()
    fun isAlive(): Boolean
}