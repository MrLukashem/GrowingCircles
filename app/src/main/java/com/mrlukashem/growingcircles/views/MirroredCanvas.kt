package com.mrlukashem.growingcircles.views

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF


class MirroredCanvas(canvas: Canvas, private val displayHeight: Int, private val displayWidth: Int)
    : CanvasDecorator(canvas) {

    override fun drawOval(oval: RectF?, paint: Paint?) {
        super.drawOval(oval, paint)
    }
}