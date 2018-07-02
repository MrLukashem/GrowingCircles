package com.mrlukashem.growingcircles.gameobjects

import android.graphics.*
import android.util.Log

import com.mrlukashem.growingcircles.drawable.DrawableShape


class RadialShadowDecorator(shape: DrawableShape) : DrawableShapeDecorator(shape) {

    private val shadowPaint: Paint = Paint()
    private var shaderRadius: Float = 0f
    private var center: PointF = PointF()

    override fun draw(canvas: Canvas) {
        //Log.e("nnm,n,mn,m", "drawwwwww")
        canvas.drawCircle(center.x, center.y, shaderRadius, shadowPaint)

        super.draw(canvas)
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        shaderRadius = ownShape.boundsRadius + ownShape.boundsRadius * 0.1f
        updateCenterPosition()
        shadowPaint.shader =
                RadialGradient(
                        center.x, center.y,
                        shaderRadius, intArrayOf(Color.BLACK,
                        Color.TRANSPARENT),
                        floatArrayOf(.7f, 1f),
                        Shader.TileMode.CLAMP)

        super.onFrameOccurred(frameTimeMillis, deltaTimeMillis)
    }

    private fun updateCenterPosition() {
        center = PointF(
                ownShape.position.x + shaderRadius * .1f,
                ownShape.position.y + shaderRadius * .1f
        )
    }
}