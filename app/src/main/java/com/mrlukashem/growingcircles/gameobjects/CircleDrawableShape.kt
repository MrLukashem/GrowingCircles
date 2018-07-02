package com.mrlukashem.growingcircles.gameobjects

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.mrlukashem.growingcircles.drawable.DrawableShape

import kotlin.math.pow


typealias HasCollisionStrategy = ((Shape, Shape) -> Boolean)

open class CircleDrawableShape(
        override var boundsRadius: Float,
        override var position: PointF,
        rgbColor: Int) : DrawableShape {

    var radius: Float
        get() = boundsRadius
        set(value) {
            boundsRadius = value
        }

    override val paint = Paint()

    init {
        paint.style = Paint.Style.FILL
        paint.color = rgbColor
    }

    override fun move(x: Float, y: Float) {
        position.x += x
        position.y += y
    }

    override fun hasCollisionWith(shape: Shape, hasCollisionStrategy: (Shape, Shape) -> Boolean)
            : Boolean = hasCollisionStrategy(this, shape)

    override fun contains(x: Float, y: Float) = (circleProduct(x, y) <= radius.pow(2f))

    private fun circleProduct(x: Float, y: Float): Float {
        return (x - position.x).pow(2f) + (y - position.y).pow(2f)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(position.x, position.y, radius, paint)
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
    }
}