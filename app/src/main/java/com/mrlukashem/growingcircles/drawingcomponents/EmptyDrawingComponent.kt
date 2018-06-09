package com.mrlukashem.growingcircles.drawingcomponents

import android.graphics.Canvas
import android.graphics.Paint

import com.mrlukashem.growingcircles.gameobjects.GameObject


class EmptyDrawingComponent : DrawingComponent {

    override fun draw(canvas: Canvas, paint: Paint) {}

    override fun has(gameObject: GameObject): Boolean = false
}