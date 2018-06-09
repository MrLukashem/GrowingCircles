package com.mrlukashem.growingcircles.drawingcomponents

import android.graphics.Canvas
import android.graphics.Paint

import com.mrlukashem.growingcircles.gameobjects.CircleObject
import com.mrlukashem.growingcircles.gameobjects.GameObject


class CircleDrawingComponent(private val mCircleObject: CircleObject) : DrawingComponent {

    override fun draw(canvas: Canvas, paint: Paint) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun has(gameObject: GameObject): Boolean = (gameObject === mCircleObject)
}