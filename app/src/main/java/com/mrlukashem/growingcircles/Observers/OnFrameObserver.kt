package com.mrlukashem.growingcircles.Observers

interface OnFrameObserver {
    fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long)
}
