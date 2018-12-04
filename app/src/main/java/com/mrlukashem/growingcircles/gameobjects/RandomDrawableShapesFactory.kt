package com.mrlukashem.growingcircles.gameobjects

import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
import android.util.Log
import android.view.Display
import com.mrlukashem.growingcircles.drawable.DrawableShape

import com.mrlukashem.growingcircles.views.size

import java.util.*
import kotlin.math.*


class RandomDrawableShapesFactory(private val gameDisplay: Display)
    : DrawableShapesFactory {
    private val random: Random = Random()
    private val colorsList: List<Int> = listOf(Color.rgb(255, 255, 0),
        Color.rgb(199, 204, 0),
            Color.rgb( 197, 17, 98),
            Color.rgb(199, 205, 224),
            Color.rgb(250, 250, 250),
            Color.rgb(0, 184, 212),
            Color.rgb(244, 67, 54),
            Color.rgb(255, 255, 0),
            Color.rgb(199, 204, 0),
            Color.rgb( 197, 17, 98),
            Color.rgb(199, 205, 224),
            Color.rgb(250, 250, 250),
            Color.rgb(0, 184, 212),
            Color.rgb(109, 76, 65),
            Color.rgb(109, 76, 65),
            Color.rgb(244, 67, 54))

    override fun create(shapeType: DrawableShapesFactory.ShapeType,
                        actualStorage: MutableList<DrawableShape>): DrawableShape {
        return when (shapeType) {
            DrawableShapesFactory.ShapeType.CIRCLE_OBJECT -> makeCircleObject(actualStorage)
        }
    }

    private fun makeCircleObject(existingShapes: MutableList<DrawableShape>): DrawableShape {
        val screenBoundsCollisionStrategy = ScreenBoundsCollisionStrategy(
                ::lowToleranceCollisionStrategy, gameDisplay)
        val shape = makeCircleObjectInternal()
        val collidedShape = existingShapes.find {
            it.hasCollisionWith(shape, screenBoundsCollisionStrategy::collisionOccurred)
        }
        collidedShape?.let {
            return makeCircleObject(existingShapes)
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
        return abs((nextInt() % (intRange.last - intRange.start)) + intRange.start)
    }

    private fun Random.nextDouble(from: Double, to: Double): Double {
        return from + (nextFloat() * abs(to - from))
    }

    private fun height() = gameDisplay.size().y

    private fun width() = gameDisplay.size().x

    private fun radiusMax() = (min(height(), width()) * 0.15).toInt()

    private fun radiusMin() = (min(height(), width()) * 0.1).toInt()

    private fun makeCircleObjectInternal(): DrawableShape {
        val radius = random.nextInt(radiusMin()..radiusMax())
        val c = (radius * 2)
        val d = (width() - radius * 2)
        val xPosition = random.nextInt(radius .. (width() - radius))
        val yPosition = random.nextInt(radius .. (height() - radius))

        Log.e("factory", "x = $xPosition, y  = $yPosition, r = $radius")
        return GrowingCircleDrawableShape(PointF( xPosition.toFloat(), yPosition.toFloat()), radius.toFloat(),
                colorsList[random.nextInt(0 .. 12)])
    }

    private fun Point.distanceTo(point: Point): Float {
        return ((point.x - x).toFloat().pow(2) + (point.y - y).toFloat().pow(2)).pow(0.5f)
    }

    private fun randomAlpha(): Float = random.nextDouble(0.0, 2.0 * PI).toFloat()

    private fun lowToleranceCollisionStrategy(firstShape: Shape, secondShape: Shape): Boolean {
        var lowToleranceRadius = firstShape.boundsRadius + firstShape.boundsRadius * .2f
        val largerRadiusFirstShape = CircleDrawableShape(
                lowToleranceRadius, firstShape.position, 0)

        lowToleranceRadius = secondShape.boundsRadius + secondShape.boundsRadius * .2f
        val largerRadiusSecondShape = CircleDrawableShape(
                lowToleranceRadius, secondShape.position, 0)

        return largerRadiusFirstShape.hasCollisionWith(largerRadiusSecondShape)
    }
}