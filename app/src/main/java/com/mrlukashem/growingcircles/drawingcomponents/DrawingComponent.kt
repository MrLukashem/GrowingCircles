package com.mrlukashem.growingcircles.drawingcomponents

import android.graphics.Canvas
import android.graphics.Paint

import com.mrlukashem.growingcircles.gameobjects.GameObject


interface DrawingComponent {
    fun draw(canvas: Canvas, paint: Paint)
    fun has(gameObject: GameObject): Boolean
}