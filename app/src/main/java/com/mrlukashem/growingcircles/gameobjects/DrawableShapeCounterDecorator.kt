package com.mrlukashem.growingcircles.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import com.mrlukashem.growingcircles.counters.ValueCounter
import com.mrlukashem.growingcircles.drawable.DrawableShape


class DrawableShapeCounterDecorator(private val pointsCounter: ValueCounter<Shape>,
                                    shape: DrawableShape,
                                    colorRGB: Int = Color.BLACK)
    : DrawableShapeDecorator(shape) {

    private val textPaint = Paint()

    init {
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.color = colorRGB
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        textPaint.textSize = 0.4f * boundsRadius
        canvas.drawText(pointsCounter.calculate(this).toString(), position.x, position.y,
                textPaint)
    }
}