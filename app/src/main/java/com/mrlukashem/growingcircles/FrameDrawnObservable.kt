package com.mrlukashem.growingcircles

interface FrameDrawnObservable {
    fun registerObserver(observer: FrameDrawnObserver)
    fun removeObserver(observer: FrameDrawnObserver)
}