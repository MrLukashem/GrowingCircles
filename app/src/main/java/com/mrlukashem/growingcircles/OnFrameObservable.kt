package com.mrlukashem.growingcircles

interface OnFrameObservable {
    fun registerObserver(observer: OnFrameObserver)
    fun removeObserver(observer: OnFrameObserver)
}