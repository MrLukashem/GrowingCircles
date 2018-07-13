package com.mrlukashem.growingcircles.gameobjects

import android.graphics.PointF

import com.mrlukashem.growingcircles.Observers.OnFrameObserver


class GrowingCircleDrawableShape(override var position: PointF,
                                 radius: Float,
                                 rgbColor: Int)
    : CircleDrawableShape(
        radius, position, rgbColor), OnFrameObserver {

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        deltaTimeMillis.takeIf {
            it > 0
        }?.let {
            val seconds = deltaTimeMillis.toSeconds()
            updateRadius(seconds)

        }
    }

    private fun updateRadius(deltaTimeSeconds: Float) {
        radius += (radius * 0.2f * deltaTimeSeconds)
    }

    private fun Long.toSeconds(): Float = (this / 1000f)
}