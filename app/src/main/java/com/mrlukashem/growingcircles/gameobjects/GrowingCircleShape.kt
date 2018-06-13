package com.mrlukashem.growingcircles.gameobjects

import com.mrlukashem.growingcircles.OnFrameObserver


class GrowingCircleShape(override var xPosition: Float, override var yPosition: Float,
                         radius: Float)
    : CircleShape(radius, xPosition, yPosition), OnFrameObserver {

    private var millisCounter: Long = 0

    override fun move(x: Float, y: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasCollisionWith(shape: Shape) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        if (millisCounter >= 1000) {
            millisCounter = 0
            radius += 10f
        }

        millisCounter += deltaTimeMillis
    }
}