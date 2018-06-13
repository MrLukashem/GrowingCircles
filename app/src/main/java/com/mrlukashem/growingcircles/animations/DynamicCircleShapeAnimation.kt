package com.mrlukashem.growingcircles.animations

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.mrlukashem.growingcircles.gameobjects.Shape

import com.mrlukashem.growingcircles.gameobjects.toDisplaySpace


class DynamicCircleShapeAnimation(private val shape: Shape, rgbColor: Int) : AnimatedComponent {

    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL
        paint.color = rgbColor
    }

    override fun draw(canvas: Canvas) {
        canvas.drawOval(makeBoundingRect(shape), paint)
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {

    }

    private fun makeBoundingRect(shape: Shape): RectF {
        val convertedShape = shape.toDisplaySpace()

        return RectF(
                convertedShape.xPosition - shape.boundsHeight,
                convertedShape.yPosition - shape.boundsHeight,
                convertedShape.xPosition + shape.boundsHeight,
                convertedShape.yPosition + shape.boundsHeight)
    }
}