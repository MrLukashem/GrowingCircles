package com.mrlukashem.growingcircles.gameobjects


class GrowingShapeObject(override val xPosition: Float, override val yPosition: Float, radius: Float)
    : ShapeObject {

    override var boundsHeight: Float = radius

    override var boundsWidth: Float = radius

    override fun move(x: Float, y: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasCollisionWith(shapeObject: ShapeObject) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var sum: Long = 0

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        if (sum >= 1000) {
            sum = 0
            boundsHeight += 10f
            boundsWidth += 10f
        }

        sum += deltaTimeMillis
    }
}