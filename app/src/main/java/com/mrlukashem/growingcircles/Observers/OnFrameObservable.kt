package com.mrlukashem.growingcircles.Observers

interface OnFrameObservable {
    fun registerObserver(observer: OnFrameObserver)
    fun removeObserver(observer: OnFrameObserver)
}