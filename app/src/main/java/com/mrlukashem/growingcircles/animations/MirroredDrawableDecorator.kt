package com.mrlukashem.growingcircles.animations

import android.graphics.Canvas
import android.view.Display

import com.mrlukashem.growingcircles.views.MirroredCanvas


class MirroredDrawableDecorator(private val display: Display, drawable: Drawable)
    : DrawableDecorator(drawable) {

    override fun draw(canvas: Canvas) {
        val mirroredCanvas: Canvas = MirroredCanvas(canvas, display)
        super.draw(mirroredCanvas)
    }
}