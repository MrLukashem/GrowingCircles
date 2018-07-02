package com.mrlukashem.growingcircles.animations

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.Log

import com.mrlukashem.growingcircles.drawable.DrawableShape
import com.mrlukashem.growingcircles.utils.*
import com.mrlukashem.growingcircles.views.ReplacedPaintCanvas

import kotlin.math.PI
import kotlin.math.abs


// TODO: Animations dispatcher?
class MovingShapeAnimation(entryShape: DrawableShape,
                           movementRadius: Float,
                           private val destination: PointF,
                           private val totalTimeMillis: Long)
    : AnimatedComponent() {

    override val coordinates: PointF
        get() = animatedShape.position

    private var currentTimeMillis: Long = 0

    private val paint: Paint = Paint()
    private val animatedShape: DrawableShape = entryShape
    private val rotationAngle: Float
    private val ellipseCenter: PointF = destination.midPoint(entryShape.position)
    private val x0: Float = entryShape.position.x
    private val a: Float// = movementRadius
    private val b: Float// = entryShape.position.distanceTo(destination) / 2f

    init {
        paint.color = Color.argb(150, 238, 255, 65)
        paint.style = Paint.Style.FILL
        rotationAngle = calculateRotationAngle(entryShape.position.toVectorWith(destination))

        val W = entryShape.position.x - destination.x
        val Wx = entryShape.position.y - destination.y
        val Wy = entryShape.position.x * destination.y - entryShape.position.y * destination.x
        a = Wx / W
        b = Wy / W
    }

    override fun draw(canvas: Canvas) {
        animatedShape.draw(canvas)
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        if (currentTimeMillis <= totalTimeMillis) {
            animatedShape.position.x = x(currentTimeMillis)
            animatedShape.position.y = y(animatedShape.position.x)
            animatedShape.boundsRadius = radius(currentTimeMillis)
            Log.e("wqewqe", "x = ${animatedShape.position.x} y = ${animatedShape.position.y}")
        } else {
            onAnimationCompleted()
        }

        currentTimeMillis += deltaTimeMillis
    }

    private fun calculateRotationAngle(aVec: Vector2D): Float  {
        return if (ellipseCenter.x >= 0) {
            abs(aVec.vectorsAngle(yAxisVec()) - PI.toFloat())
        } else {
            aVec.vectorsAngle(yAxisVec())
        }
    }

        private fun yAxisVec() = Vector2D(0f, 1f)

      //  private fun rotatedX(t: Float): Float = x0 + (x(t) * cos(rotationAngle) - y(t) * sin(rotationAngle))

        //private fun rotatedY(t: Float): Float = y0 + (x(t) * sin(rotationAngle) + y(t) * cos(rotationAngle))

        private fun x(currentTimeMillis: Long): Float {
            return x0 + (currentTimeMillis.toFloat() / totalTimeMillis.toFloat()) * (destination.x - x0)//ellipseCenter.x + a * cos(t)
        }

        private fun radius(currentTimeMillis: Long): Float {
            return (1f - (currentTimeMillis.toFloat() / totalTimeMillis.toFloat())) * animatedShape.boundsRadius
        }

        private fun y(x: Float): Float = a * x + b//ellipseCenter.y + b * sin(t)

        private fun t(currentTimeMillis: Long): Float {
            val t = (currentTimeMillis.toFloat() / totalTimeMillis.toFloat()) * PI.toFloat() * -1f + .5f * PI.toFloat()
            Log.e("qwe", "t = $t")
            return t
        }

}