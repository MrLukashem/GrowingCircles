package com.mrlukashem.growingcircles.animations

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.mrlukashem.growingcircles.gameobjects.ShapeObject

import com.mrlukashem.growingcircles.gameobjects.toDisplaySpace

class CircleShapeAnimation(rgbColor: Int) : AnimationComponent() {

    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL
        paint.color = rgbColor
    }

    override fun draw(canvas: Canvas) {
        attachedObject?.let {
            canvas.drawOval(makeBoundingRect(it), paint)
        }
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {

    }

    private fun makeBoundingRect(shapeObject: ShapeObject): RectF {
        val convertedShape = shapeObject.toDisplaySpace()

        return RectF(
                convertedShape.xPosition - shapeObject.boundsHeight.toFloat(),
                convertedShape.yPosition - shapeObject.boundsHeight.toFloat(),
                convertedShape.xPosition + shapeObject.boundsHeight.toFloat(),
                convertedShape.yPosition + shapeObject.boundsHeight.toFloat())
    }
}