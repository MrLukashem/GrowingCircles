package com.mrlukashem.growingcircles.gameobjects

import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
import android.view.Display
import com.mrlukashem.growingcircles.drawable.DrawableShape

import com.mrlukashem.growingcircles.views.size

import java.util.*
import kotlin.math.*


class RandomDrawableShapesFactory(private val existingShapes: List<Shape>, private val gameDisplay: Display)
    : DrawableShapesFactory {

    private val random: Random = Random()

    override fun create(shapeType: DrawableShapesFactory.ShapeType): DrawableShape {
        return when (shapeType) {
            DrawableShapesFactory.ShapeType.CIRCLE_OBJECT -> makeCircleObject()
        }
    }

    private fun makeCircleObject(): DrawableShape {
        val shape = makeCircleObjectInternal()
        val collidedShape = existingShapes.find {
            it.hasCollisionWith(shape)
        }
        collidedShape?.let {
            return makeCircleObject()
        }

//        val horizontalStep: Int = width() / 4
//        val verticalStep: Int = height() / 6
//        val shapes: MutableList<DrawableShape> = mutableListOf()
//
//        for (y in 0 .. height() step verticalStep) {
//            for(x in 0 .. width() step horizontalStep) {
//                val x2: Int = x
//                val y2: Int = verticalStep
//                val x1: Int = x
//                val y1: Int = y
//                val x4: Int = x + horizontalStep
//                val y4: Int = y
//                val x3: Int = x + horizontalStep
//                val y3: Int = y + verticalStep
//
//                val s: Point = Point((((x4 - x1) / 2) + x1) + random.nextInt(0 .. 20),((y2 - y1) / 2) + y1 + + random.nextInt(0 .. 20))
//                val triangleVertex: Point = Point(((x4 - x1) / 2) + x1, y1)
//
//                val a: Float = Point(x2, y2).distanceTo(Point(x3, y3))
//                val b: Float = triangleVertex.distanceTo(Point(x3, y3))
//                val c: Float = Point(x2, y2).distanceTo(triangleVertex)
//                val p: Float = (a + b + c) / 2f
//                val P: Float = (p * (p - a) * (p - b) * (p - c)).pow(0.5f)
//                val maxRadius: Float = (2f * P) / (a + b + c)
//                val minRadius: Float = 0.5f * maxRadius
//
//                val radius: Float = random.nextDouble(minRadius.toDouble(), maxRadius.toDouble()).toFloat()
//                shapes.add(GrowingCircleDrawableShape(s.x.toFloat(), s.y.toFloat(), radius.toFloat(),
//                        ::defaultCircleBasedCollisionStrategy, Color.argb(100, random.nextInt(1 .. 360), 0, 238)))
//            }
//        }

        return shape
    }

    private fun Random.nextInt(intRange: IntRange): Int {
        return abs((nextInt() % intRange.last) + intRange.start)
    }

    private fun Random.nextDouble(from: Double, to: Double): Double {
        return from + (nextFloat() * abs(to - from))
    }

    private fun height() = gameDisplay.size().y

    private fun width() = gameDisplay.size().x

    private fun radiusMax() = (min(height(), width()) * 0.1).toInt()

    private fun radiusMin() = (min(height(), width()) * 0.05).toInt()

    private fun maxXPosition() = width() - (width() * 0.1).toInt()

    private fun minXPosition() = (width() * 0.1).toInt()

    private fun makeCircleObjectInternal(): DrawableShape {
        val xPosition = random.nextInt(minXPosition()..maxXPosition())
        val yPosition = random.nextInt(0..height())
        val radius = random.nextInt(radiusMin()..radiusMax())

        return GrowingCircleDrawableShape(PointF( xPosition.toFloat(), yPosition.toFloat()), radius.toFloat(),
                        ::defaultCircleBasedCollisionStrategy, Color.argb(50, random.nextInt(1 .. 360), 0, 238))
    }

    private fun Point.distanceTo(point: Point): Float {
        return ((point.x - x).toFloat().pow(2) + (point.y - y).toFloat().pow(2)).pow(0.5f)
    }

    private fun randomAlpha(): Float = random.nextDouble(0.0, 2.0 * PI).toFloat()
}