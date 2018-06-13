package com.mrlukashem.growingcircles.views

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.Display


class MirroredCanvas(canvas: Canvas, private val display: Display)
    : CanvasDecorator(canvas) {

    override fun drawOval(oval: RectF?, paint: Paint?) {
        super.drawOval(oval, paint)
    }
}