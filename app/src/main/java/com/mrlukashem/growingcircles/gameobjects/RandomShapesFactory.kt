package com.mrlukashem.growingcircles.gameobjects

import android.view.Display
import com.mrlukashem.growingcircles.size

import java.util.*

import kotlin.math.abs


class RandomShapesFactory(existings: List<Shape>, private val gameDisplay: Display)
    : ShapesFactory {

    private val existings: MutableList<Shape> = mutableListOf()
    private val random: Random = Random()

    init {
        this.existings.addAll(existings)
    }

    override fun create(shapesType: ShapesFactory.ShapeType): Shape {
        return when (shapesType) {
            ShapesFactory.ShapeType.CIRCLE_OBJECT -> makeCircleObject()
        }
    }

    private fun makeCircleObject(): Shape {
        val xPosition = random.nextInt(0..width())
        val yPosition = random.nextInt(0..height())
        val radius = random.nextInt(10..50)

        val circleObject = GrowingCircleShape(xPosition.toFloat(), yPosition.toFloat(),
                radius.toFloat())
        existings.add(circleObject)

        return circleObject
    }

    private fun Random.nextInt(intRange: IntRange): Int {
        return abs((nextInt() % intRange.last) + intRange.start)
    }

    private fun height() = gameDisplay.size().y

    private fun width() = gameDisplay.size().x
}