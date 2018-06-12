package com.mrlukashem.growingcircles.views

interface FrameDrawnObservable {
    fun registerObserver(observer: FrameDrawnObserver)
    fun removeObserver(observer: FrameDrawnObserver)
}