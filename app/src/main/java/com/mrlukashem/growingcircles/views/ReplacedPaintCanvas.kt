package com.mrlukashem.growingcircles.views

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF


class ReplacedPaintCanvas(canvas: Canvas, private val replacedPaint: Paint)
    : CanvasDecorator(canvas) {

    override fun drawOval(oval: RectF?, paint: Paint?) {
        canvas.drawOval(oval, replacedPaint)
    }
}