package com.mrlukashem.growingcircles.animations

import android.graphics.Canvas


abstract class DrawableDecorator(protected val drawable: Drawable) : Drawable {

    override fun draw(canvas: Canvas) {
        drawable.draw(canvas)
    }
}