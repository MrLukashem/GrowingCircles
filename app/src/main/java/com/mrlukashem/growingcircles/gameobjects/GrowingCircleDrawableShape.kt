package com.mrlukashem.growingcircles.gameobjects

import android.graphics.PointF

import com.mrlukashem.growingcircles.Observers.OnFrameObserver


class GrowingCircleDrawableShape(override var position: PointF,
                                 radius: Float, hasCollisionStrategy: HasCollisionStrategy,
                                 rgbColor: Int)
    : CircleDrawableShape(
        radius, position, rgbColor), OnFrameObserver {

    var counter = 0

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        counter++
        deltaTimeMillis.takeIf {
            it > 0
        }?.let {
            val seconds = deltaTimeMillis.toSeconds()
            updateRadius(seconds)

        }
    }

    private fun updateRadius(deltaTimeSeconds: Float) {
        radius += (radius * 0.3f * deltaTimeSeconds)
    }

    private fun Long.toSeconds(): Float = (this / 1000f)
}