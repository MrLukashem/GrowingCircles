package com.mrlukashem.growingcircles.gameobjects

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.mrlukashem.growingcircles.drawable.DrawableShape


abstract class DrawableShapeDecorator(protected val ownShape: DrawableShape) : DrawableShape {

    override var boundsRadius: Float
        get() = ownShape.boundsRadius
        set(value) {
            ownShape.boundsRadius = value
        }
    override var position: PointF
        get() = ownShape.position
        set(value) {
            ownShape.position = value
        }

    override val paint: Paint
        get() = ownShape.paint

    override fun move(x: Float, y: Float) {
        ownShape.move(x, y)
    }

    override fun hasCollisionWith(shape: Shape, hasCollisionStrategy: (Shape, Shape) -> Boolean)
            : Boolean = ownShape.hasCollisionWith(shape, hasCollisionStrategy)

    override fun contains(x: Float, y: Float): Boolean = ownShape.contains(x, y)

    override fun draw(canvas: Canvas) {
        ownShape.draw(canvas)
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        ownShape.onFrameOccurred(frameTimeMillis, deltaTimeMillis)
    }

}