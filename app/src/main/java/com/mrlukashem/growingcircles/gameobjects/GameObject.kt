package com.mrlukashem.growingcircles.gameobjects

import android.graphics.Canvas

import com.mrlukashem.growingcircles.drawingcomponents.DrawingComponent


abstract class GameObject {
    protected abstract val mDrawingComponent: DrawingComponent

    abstract fun move(x: Double, y: Double)
    abstract fun updateSize(percentage: Double)

    fun draw(canvas: Canvas) {
        mDrawingComponent.draw(canvas)
    }
}