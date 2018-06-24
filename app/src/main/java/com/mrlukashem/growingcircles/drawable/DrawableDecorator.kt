package com.mrlukashem.growingcircles.drawable

import android.graphics.Canvas
import com.mrlukashem.growingcircles.drawable.Drawable


abstract class DrawableDecorator(protected val drawable: Drawable) : Drawable {

    override fun draw(canvas: Canvas) {
        drawable.draw(canvas)
    }
}