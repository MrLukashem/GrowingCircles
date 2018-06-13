package com.mrlukashem.growingcircles.gameobjects


open class CircleShape(
        protected var radius: Float,
        override var xPosition: Float,
        override var yPosition: Float) : Shape {

    override val boundsHeight: Float
        get() = radius

    override val boundsWidth: Float
        get() = radius

    override fun move(x: Float, y: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasCollisionWith(shape: Shape) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}