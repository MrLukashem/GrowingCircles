package com.mrlukashem.growingcircles.views

import com.mrlukashem.growingcircles.drawable.Drawable


interface DrawingComponent {
    fun addDrawable(drawable: Drawable)
    fun removeDrawable(drawable: Drawable)
}
