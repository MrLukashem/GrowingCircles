package com.mrlukashem.growingcircles

interface OnFrameObserver {
    fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long)
}
