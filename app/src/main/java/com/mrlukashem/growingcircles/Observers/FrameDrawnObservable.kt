package com.mrlukashem.growingcircles.Observers

interface FrameDrawnObservable {
    fun registerObserver(observer: FrameDrawnObserver)
    fun removeObserver(observer: FrameDrawnObserver)
}